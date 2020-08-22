# soft oscilloscope - User guide

A free desktop oscilloscope. 

Vincent Perrier 2020

## Before getting started

You will need to install the Arduino IDE available [here](https://www.arduino.cc/).

You will also need a java runtine of at least [version 8](https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html).

## Programming an arduino

In the project, open ```softOscilloscope > arduino > ArduinoPacketGenerator.ino``` with the arduino IDE and upload that to your board.
You should put 10k ohms resistors on all channels to act as pulldowns. This is to avoid crosstalk between the arduino's analog inputs.

## Running the binary

Simply go to [the releases page on github](https://github.com/Perceval62/softOscilloscope/releases) and
fetch one of the available prebuilt binaries. Once acquired, assuming java is installed you can run the program.


