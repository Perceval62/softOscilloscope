#ifndef SERIALPORTQTIMPL_H
#define SERIALPORTQTIMPL_H

#include <portstrategy.h>
#include <QSerialPort>

class SerialPortQtImpl: public PortStrategy
{
public:
    SerialPortQtImpl();
    std::vector<unsigned char> readData() const override;
};

#endif // SERIALPORTQTIMPL_H
