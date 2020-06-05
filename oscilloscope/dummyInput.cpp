#include "dummyInput.h"

#include <iostream>
#include <math.h>
#include <thread>

DummyInput::DummyInput(QObject *parent) : QObject(parent), PortStrategy()
{

}

void DummyInput::initialize() const
{

}

std::vector<unsigned char> * DummyInput::readData() const
{
    std::cout << "reading data from dummy input"<< std::endl;
    auto ret = new std::vector<unsigned char>();
    for(int i = 0; i < 100; i++)
    {
        ret->push_back(5*sin(i));
    }
    return ret;
}
