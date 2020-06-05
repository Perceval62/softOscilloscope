#include "mainview.h"

#include <QApplication>

#include "portstrategy.h"
#include "dummyInput.h"
#include "waveform.h"

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    auto port = (DummyInput *) PortStrategy::createPort(AvailableImpl::dummy, "yeet");
    WaveForm model(port);
    MainView w(nullptr, &model);
    port->notifyListeners();
    w.show();
    return a.exec();
}


