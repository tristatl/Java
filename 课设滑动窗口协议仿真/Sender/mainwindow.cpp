#include "mainwindow.h"
#include "ui_mainwindow.h"
#include "QtNetwork/QTcpSocket"
#include <QMessageBox>
#include <iostream>
#include "QTimer"
using namespace std;


MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    ui->setupUi(this);

}

MainWindow::~MainWindow()
{
    delete ui;
}

//连接
void MainWindow::on_pushButton_clicked()
{
    this->socket = new QTcpSocket(this);
    this->socket->connectToHost("127.0.0.1",8888,QTcpSocket::ReadWrite);
    connect(this->socket,SIGNAL(connected()),this,SLOT(run()));
}

//通信
void MainWindow::run(){

    //读取错误帧和丢失数据帧
    QStringList error_seq_list = ui->lineEdit->text().split(',');
    QStringList lost_seq_list = ui->lineEdit_2->text().split(',');

    //窗口大小，发送定时器，超时定时器
    this->windowSize = ui->lineEdit_3->text().toInt();
    this->timerTime = ui->lineEdit_4->text().toInt();
    this->timeoutTime = ui->lineEdit_5->text().toInt();

    this->lastId=0;

    //发送帧序列，错误帧标记为1，丢失帧标记为2
    memset(seq,0,sizeof(seq));
    foreach(QString a,error_seq_list){
        this->seq[a.toInt()]=1;
    }
    foreach(QString a,lost_seq_list){
        this->seq[a.toInt()]=2;
    }

    //定时器
    QTimer *timer=new QTimer(this);
    connect(timer,SIGNAL(timeout()),SLOT(TimerUpdate()));
    timer->start(timerTime);
}

//更新定时器
void MainWindow::TimerUpdate(){
    //检查超时帧
    if(checkSeqTimeout()){
        SendOne();
    }
    //接收应答
    recv();
}

//发送一个数据帧
void MainWindow::SendOne(){

    //当前窗口大小小于设置的窗口大小
    if(window.size() < windowSize){
        window.push_back(Frame(lastId+1));
        if(this->seq[lastId%windowSize] == 0 )
            Send(lastId+1);
        else if(this->seq[lastId%windowSize] == 1)
            SendErrorFrame(lastId+1);
        lastId++;
    }
}

//检查序列是否超时
bool MainWindow::checkSeqTimeout(){
    QTime now= QTime::currentTime();
    bool flag=0;
    int cnt=0;
    while(!this->window.isEmpty()){
        if(this->window.front().isAck == 1)
            this->window.pop_front();
        else
            break;
    }
    for(Frame &a:this->window){
        if(a.timestamp.msecsTo(now) > timeoutTime){
            Resend(a.id);
            flag=1;
        }
    }
    return !flag;
}

//发送正常数据帧
void MainWindow::Send(int id){
    QByteArray arr;
    QDataStream out(&arr,QIODevice::WriteOnly);
    QString str;
    ui->cur_frame->setText(QString::number(id,10));
    ui->beg_window->setText(QString::number(window.front().id,10));
    ui->end_window->setText(QString::number(window.front().id+windowSize,10));

    str.sprintf("Frame %d",id);
    //cout <<"Send a " << str.toStdString()<<endl;
    out << str;
    this->socket->write(arr);
}

//发送错误帧
void MainWindow::SendErrorFrame(int id){
    QByteArray arr;
    QDataStream out(&arr,QIODevice::WriteOnly);
    QString str;
    str.sprintf("Error Frame %d",id);
    out << str;
    this->socket->write(arr);
}

//重新发送
void MainWindow::Resend(int id){
    QByteArray arr;
    QDataStream out(&arr,QIODevice::WriteOnly);
    QString str;
    str.sprintf("Resend Frame %d",id);
    cout << str.toStdString() << endl;
    out << str;
    this->socket->write(arr);
}

//接收应答信息
void MainWindow::recv(){
    QByteArray arr=this->socket->readAll();
    //cout << arr.size() << endl;
    QDataStream in(arr);
    QString str;
    while(!in.atEnd()){
        in >> str;
        ui->listWidget->addItem(new QListWidgetItem(str));
        ui->listWidget->scrollToBottom();
        QStringList sl= str.split(' ');
        foreach(QString s,sl)
            cout << s.toStdString() << endl;
        if(sl.at(0) == "Error")
            Resend(sl.at(1).toInt());
        else if(sl.at(0) == "Timeout"){
            Resend(sl.at(1).toInt());
        }
        else if(sl.at(0) == "ACK"){
            SetAck(sl.at(1).toInt());
        }
    }
}

//设置收到的应答信息
void MainWindow::SetAck(int id){
    cout << "Set ACK " << id << endl;
    for(Frame &t:window){
        if(t.id < id)
            t.isAck=1;
    }
}

void MainWindow::on_exit_clicked()
{
    QApplication::exit();
}

void MainWindow::on_pushButton_2_clicked()
{
    this->socket->close();
}
