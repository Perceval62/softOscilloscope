#include "waveform.h"
#include "portstrategy.h"
#include "serialportqtimpl.h"

WaveForm::WaveForm(PortStrategy * newSource):
    portName(nullptr),
    source(nullptr)
{
    setSource(newSource);
}

//Adds an object to callback when calling notifyObserver
void WaveForm::addListener(Observer * o)
{
    if (o != NULL ) this->observers.push_back(o);
}

//This calls the update() method on every registered listener
void WaveForm::notifyListeners() const
{
    for( auto t: this->observers) t->update();
}
//sets the private variables to the given value
void WaveForm::setSource(PortStrategy * newSource)
{
    if (newSource != nullptr)
    {
        this->source = newSource;
        this->source->addListener(this);
    }
}

//Return the name of the
std::string WaveForm::name() const
{
    if(this->portName != nullptr){
        return *this->portName;
    }else{
        return "";
    }
}

void WaveForm::update()
{
    this->bytes = getPacket();
    this->notifyListeners();
}

std::vector<unsigned char> * WaveForm::getPacket() const
{
    return this->source->readData();
}
