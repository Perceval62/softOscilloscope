#include "serialportqtimpl.h"
#include <QSerialPort>
#include <QString>

#include "observer.h"

SerialPortQtImpl::SerialPortQtImpl(QObject *parent, std::string portName):
    QObject(parent),
    PortStrategy()
{
    QString port(portName.c_str());
    this->serialPort = new QSerialPort(port);
    QObject::connect(this->serialPort, SIGNAL(readyRead()),
                     this, SLOT(readData()));
}

SerialPortQtImpl::~SerialPortQtImpl()
{

}

void SerialPortQtImpl::initialize() const
{
    this->serialPort->open(QSerialPort::OpenModeFlag::ReadOnly);
}

std::vector<unsigned char> * SerialPortQtImpl::readData() const
{
    auto a = new std::vector<unsigned char>();
    return a;
}
