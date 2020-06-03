#include "mainview.h"
#include "ui_mainview.h"


MainView::MainView(QWidget *parent)
    : QWidget(parent)
    , ui(new Ui::MainView)
{
    ui->setupUi(this);
}

void MainView::update()
{
    #warning To Implement !
}

MainView::~MainView()
{
    delete ui;
}

