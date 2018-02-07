#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include "QtNetwork/qsctpsocket.h"
#include "QTime"
#include "QList"

//数据帧
struct Frame{
    QTime timestamp;
    int id;
    bool isAck;
    Frame(int id){
        this->id = id;
        this->timestamp = QTime::currentTime();
        this->isAck=0;
    }
};

namespace Ui {

class MainWindow;
}

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    explicit MainWindow(QWidget *parent = 0);
    ~MainWindow();

private slots:
   void on_pushButton_clicked();    //开始发送
   void run();

   void on_exit_clicked();          //退出

   void on_pushButton_2_clicked();      //停止发送

   void TimerUpdate();              //定时器更新

private:
    QTcpSocket *socket;
    Ui::MainWindow *ui;
    bool hasErroeFrame;
    bool hasTimeoutFrame;
    int timeoutTime;
    int timerTime;
    int lastId;
    int windowSize;
    int seq[1000];
    QList<Frame> window;        //窗口中的数据帧
    bool checkSeqTimeout();
    bool is_re_send();
    void Send(int id);
    void SendOne();
    void SendErrorFrame(int id);
    void Resend(int id);
    void recv();
    void SetAck(int id);
    int currentId;
};

#endif // MAINWINDOW_H
