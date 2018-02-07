#include "mainwindow.h"
#include "ui_mainwindow.h"
#include <QMessageBox>
#include <iostream>
#include "Qtimer"
#include "QTime"
#include "QStringList"
#include "iostream"
using namespace std;

MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    ui->setupUi(this);
    this->currentId=0;

}

MainWindow::~MainWindow()
{
    delete ui;
}

void MainWindow::on_pushButton_clicked()
{
    this->server = new QTcpServer(this);
    this->socket = 0;
    server->listen(QHostAddress::Any,8888);
    QObject::connect(this->server,SIGNAL(newConnection()),this,SLOT(newConnection()));
}
void MainWindow::newConnection(){
    this->socket=this->server->nextPendingConnection();
    connect(socket,SIGNAL(readyRead()),this,SLOT(infoFromSender()));
}

void MainWindow::infoFromSender(){
    this->windSize = ui->lineEdit->text().toInt();
    this->recvTime = ui->lineEdit_2->text().toInt();
    this->AckCheckTime = ui->lineEdit_3->text().toInt()/recvTime;

    QStringList error_seq_list = ui->lineEdit_4->text().split(',');  //出错应答帧
    QStringList lost_seq_list = ui->lineEdit_5->text().split(',');   //丢失帧
    memset(seq,0,sizeof(seq));
    foreach(QString t,error_seq_list){
        seq[t.toInt()]=1;
    }
    foreach(QString t,lost_seq_list){
        seq[t.toInt()]=2;
    }

    sendTimes = 0;
    count=0;

    QTimer *timer = new QTimer(this);
    connect(timer,SIGNAL(timeout()),SLOT(TimerUpdate()));
    timer->start(recvTime);
}

void MainWindow::TimerUpdate(){
    recv();
    count++;
    if(count == AckCheckTime)
        sendAck();
}

void MainWindow::sendAck(){
    count=0;
    qSort(this->wind);
    QListWidgetItem item;
    for(Frame &t:this->wind){
        if(t.id == currentId+1){
            currentId++;
            wind.pop_front();
        }else if(t.timestamp.msecsTo(QTime::currentTime()) > this->recvTime*5)
            sendTimeout(currentId,t.id);
    }
    QString str;
    str.sprintf("ACK %d",currentId);
    item.setText(str);
    ui->listWidget->addItem(&item);
    send(str);
}

void MainWindow::recv(){
    QByteArray arr = this->socket->readAll();
    QDataStream in(arr);
    QString str;
    while(!in.atEnd()){
        in >> str;
        cout << str.toStdString() << endl;
        ui->listWidget->addItem(new QListWidgetItem(str));
        ui->listWidget->scrollToBottom();

        QStringList sl = str.split(' ');
        if(sl.at(0) == "Frame"){
            wind.push_back(Frame(sl.at(1).toInt()));
            ui->from_label->setText(QString::number(wind.front().id,10));
            ui->to_label->setText(QString::number(wind.front().id+windSize,10));
            ui->speed_label->setText(QString::number(sl.at(1).toInt(),10));
        }else if(sl.at(0) == "Error"){
            QString str1;
            str1.sprintf("Timeout %d",sl.at(2).toInt());
            send(str1);
        }
        else if(sl.at(0) == "Resend"){
            wind.push_back(Frame(sl.at(2).toInt()));
        }

    }
}

void MainWindow::send(QString str){
    if(seq[sendTimes%windSize] != 0 )
        return;
    sendTimes++;
    QByteArray arr;
    QDataStream out(&arr,QIODevice::WriteOnly);
    out << str;
    this->socket->write(arr);
}

void MainWindow::sendTimeout(int begin,int end){
    QString str;
    for(int i=begin;i<end;i++){
        str.sprintf("Timeout %d",i);
        send(str);
    }
}

void MainWindow::on_pushButton_3_clicked()
{
    QApplication::exit();
}

void MainWindow::on_pushButton_2_clicked()
{
    this->socket->close();
}
