#ifndef SERIALPORTQTIMPL_H
#define SERIALPORTQTIMPL_H

#include <QObject>

#include <portstrategy.h>
#include <QSerialPort>
#include "observer.h"

class SerialPortQtImpl: public QObject, public PortStrategy
{
    Q_OBJECT
public:
    SerialPortQtImpl(QObject *parent = nullptr, std::string portName = "/dev/ttyACM0");
    ~SerialPortQtImpl();

    void initialize() const override;

public slots:
    std::vector<unsigned char> * readData() const override;

private:
    QSerialPort * serialPort;
};

#endif // SERIALPORTQTIMPL_H
