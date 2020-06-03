#include "mainview.h"

#include <QApplication>

int main(int argc, char *argv[])
{
    WaveFormModel model;
    QApplication a(argc, argv);
    MainView w(nullptr, &model);
    w.show();
    model.notifyListeners();
    return a.exec();
}
