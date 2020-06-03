#ifndef MAINVIEW_H
#define MAINVIEW_H

#include <QWidget>
#include "inputobserver.h"
#include "waveformmodel.h"

QT_BEGIN_NAMESPACE
namespace Ui { class MainView; }
QT_END_NAMESPACE

class MainView : public QWidget, public InputObserver
{
    Q_OBJECT

public:
    MainView(QWidget *parent = nullptr, WaveFormModel * model = nullptr );
    void update() override;
    ~MainView();

private:
    Ui::MainView *ui;
};
#endif // MAINVIEW_H
