#include <portstrategy.h>
#include <serialportqtimpl.h>
#include <dummyInput.h>
/**
 * @brief PortStrategy::createPort creates a impl of a children of the PortStrategy interface
 * @param impl  AvailableImpl, choosing wich class to instantiate.
 * @param portName the name of the port to instantiate.
 * @return
 */
PortStrategy * PortStrategy::createPort(AvailableImpl impl, std::string portName)
{
    if(impl == AvailableImpl::dummy) {return new DummyInput();}
    if(impl == AvailableImpl::SerialQtImpl) {return new SerialPortQtImpl(nullptr, portName);}
    throw "Couldn't instantiate port";
}

//Adds an object to callback when calling notifyObserver
void PortStrategy::addListener(Observer * o)
{
    if (o != NULL ) this->listeners.push_back(o);
}

//This calls the update() method on every registered listener
void PortStrategy::notifyListeners()
{
    for( auto t: this->listeners) t->update();
}

PortStrategy::PortStrategy()
{

}

PortStrategy::~PortStrategy()
{

}
