#ifndef MAINVIEW_H
#define MAINVIEW_H

#include <QtCharts>
#include <QWidget>
#include <QtCharts/QChartView>
#include <QtCharts/QLineSeries>
#include <QtCharts/QChart>
#include <QtCharts/QValueAxis>
#include "observer.h"
#include "waveform.h"

QT_BEGIN_NAMESPACE
namespace Ui { class MainView; }
QT_END_NAMESPACE

class MainView : public QWidget, public Observer
{
    Q_OBJECT

public:
    MainView(QWidget *parent = nullptr, WaveForm * model = nullptr );
    void update() override;
    ~MainView();

private:
    Ui::MainView *ui;
    QLineSeries * buffer;
    WaveForm * model;
    QChartView * chartView;
    QChart * graph;
};
#endif // MAINVIEW_H
