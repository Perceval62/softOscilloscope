#include "waveformmodel.h"
#include "serialportqtimpl.h"

WaveFormModel::WaveFormModel():
    io(new SerialPortQtImpl())
{

}

void WaveFormModel::addListener(InputObserver * o)
{
    if (o != NULL ) this->observers.push_back(o);
}

void WaveFormModel::notifyListeners() const
{
    for( InputObserver * t: this->observers) t->update();
}
