#ifndef WAVEFORMMODEL_H
#define WAVEFORMMODEL_H

#include <vector>
#include <inputobserver.h>
#include <portstrategy.h>

class WaveFormModel
{
public:
    WaveFormModel();
    void addListener(InputObserver * o);
    void notifyListeners() const;

    std::vector<char> getData() const;

private:
    std::vector<char> bytes;
    std::vector<InputObserver *> observers;

    PortStrategy * io;
};

#endif // WAVEFORMMODEL_H
