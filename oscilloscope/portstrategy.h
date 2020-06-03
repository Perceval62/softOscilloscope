#ifndef PORTSTRATEGY_H
#define PORTSTRATEGY_H

#include <vector>

enum AvailableImpl
{
    SerialQtImpl
};

class PortStrategy
{
public:
    virtual std::vector<unsigned char> readData() const = 0;
};

#endif // PORTSTRATEGY_H
