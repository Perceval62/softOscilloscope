#ifndef PORTSTRATEGY_H
#define PORTSTRATEGY_H

#include <string>
#include <vector>
#include <observer.h>

enum AvailableImpl
{
    dummy,
    SerialQtImpl
};

class PortStrategy
{
public:
    PortStrategy();
    ~PortStrategy();

    static PortStrategy * createPort(AvailableImpl impl, std::string portName);

    //This is to be called after a constructor.
    //It is a bad idea to do IO in a constructor
    virtual void initialize() const = 0;
    virtual std::vector<unsigned char> * readData() const = 0;

    void addListener(Observer * o);
    void notifyListeners();
private:
    std::vector<Observer *> listeners;
};

#endif // PORTSTRATEGY_H
