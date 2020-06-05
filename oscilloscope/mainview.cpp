#include "mainview.h"
#include "ui_mainview.h"

#include <QtCharts>
#include <QWidget>


#include <QtCharts/QChartView>
#include <QtCharts/QLineSeries>
#include <QtCharts/QChart>
#include <QtCharts/QValueAxis>


#include <iostream>

MainView::MainView(QWidget *parent, WaveForm * newModel)
    : QWidget(parent),
      Observer()
    , ui(new Ui::MainView)
{
    this->model = newModel;
    ui->setupUi(this);
    newModel->addListener(this);

    //Allocate space for the QLineSerie object
    buffer = new QLineSeries;
    //initialize the buffer with values
    float x = 0;
    for(int i = 0; i <= 100; i++, x++)
    {
        buffer->insert(i, QPointF(x, 0.0));
    }

    //Create the chart object
    this->graph = new QChart;

    //Create the graph object
    this->chartView = new QChartView(graph);
    graph->addSeries(buffer);

    //Adjust format and shown values of the axis
    auto axisX = new QValueAxis;
    axisX->setRange(0, 100);
    axisX->setLabelFormat("%g");
    axisX->setTitleText("time");

    //same goes for the 2nd axis of the graph
    auto axisY = new QValueAxis;
    axisY->setRange(-1, 1);
    axisY->setTitleText("Voltage (V)");

    graph->addAxis(axisX, Qt::AlignBottom);
    buffer->attachAxis(axisX);
    graph->addAxis(axisY, Qt::AlignLeft);
    buffer->attachAxis(axisY);
    graph->legend()->hide();

    this->ui->mainLayout->addWidget(chartView);
}

void MainView::update()
{
    auto newData = this->model->getPacket();
    graph->removeSeries(buffer);
    delete buffer;
    buffer = nullptr;
    buffer = new QLineSeries;
    for(int i = 0; i < 100; i++)
    {
        buffer->insert(i, QPointF(i, newData->at(i)));
    }
    graph->addSeries(buffer);
}

MainView::~MainView()
{
    delete ui;
}

