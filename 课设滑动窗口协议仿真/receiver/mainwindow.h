#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include "QtNetwork/qsctpsocket.h"
#include "QTime"
#include "QtNetwork/qsctpserver.h"


struct Frame{
    QTime timestamp;
    int id;
    bool isSendAck;
    Frame(int id){
        this->id = id;
        this->timestamp = QTime::currentTime();
        this->isSendAck=0;
    }
    bool operator < (const Frame &t) const {
        return this->id < t.id;
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
    QTcpServer *server;
    QTcpSocket *socket;

private slots:
    void on_pushButton_clicked();
    void newConnection();
    void infoFromSender();

    void on_pushButton_3_clicked();

    void on_pushButton_2_clicked();

    void TimerUpdate();

private:
    Ui::MainWindow *ui;
    int AckCheckTime;
    int recvTime;
    int windSize;
    int count;
    int seq[1000];
    int sendTimes;
    int currentId;
    QList<Frame> wind;
    void recv();
    void send(QString str);
    void sendAck();
    void sendTimeout(int begin,int end);
};

#endif // MAINWINDOW_H
