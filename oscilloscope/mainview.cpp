#include "mainview.h"
#include "ui_mainview.h"

#include <iostream>

MainView::MainView(QWidget *parent, WaveFormModel * model)
    : QWidget(parent),
      InputObserver()
    , ui(new Ui::MainView)
{
    ui->setupUi(this);
    model->addListener(this);
}

void MainView::update()
{
    std::cout << "Called back : D !" << std::endl;
}

MainView::~MainView()
{
    delete ui;
}

