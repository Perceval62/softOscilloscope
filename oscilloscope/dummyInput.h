#ifndef QSERIALIMPL_H
#define QSERIALIMPL_H

#include <QObject>
#include "portstrategy.h"

class DummyInput : public QObject, public PortStrategy
{
    Q_OBJECT
public:
    explicit DummyInput(QObject *parent = nullptr);
    void initialize() const override;

public slots:
    std::vector<unsigned char> * readData() const override;

};

#endif // QSERIALIMPL_H
