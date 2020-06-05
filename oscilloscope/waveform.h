#ifndef WAVEFORM_H
#define WAVEFORM_H

#include <vector>
#include <filesystem>
#include <observer.h>
#include <portstrategy.h>
#include <serialportqtimpl.h>

class WaveForm: public Observer
{
public:
    WaveForm(PortStrategy * newSource);

    void addListener(Observer * o);
    void notifyListeners() const;

    std::string name() const;
    std::vector<unsigned char> * getPacket() const;
    void update() override;

private:
    void setSource(PortStrategy * newSource);

    std::vector<unsigned char> * bytes;
    std::vector<Observer *> observers;

    const std::string * portName;
    PortStrategy * source;
};

#endif // WAVEFORMMODEL_H
