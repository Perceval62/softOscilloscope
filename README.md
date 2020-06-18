# Software oscilloscope

A free desktop oscilloscope. 

Vincent Perrier 2020

## Outline

Any hardware hobbyist knows the troubleshooting potential of
an oscilloscope. 
They're essential to any hardware project that requires more than a few LEDs. 
Unfortunately, these absolute units of diagnostic tools
can be quite costly. 
An oscilloscope works by reading a bunch of samples with an Analog 
to Digital converter (ADC), then, displays them on a screen with different zoom options. 

Modern day hardware is amazingly fast. Even a tiny raspberry pi is able to 
do billions of calculations per second, run a graphical interface and playback audio.
With many features such as these, doing basic arithmetics and 
graphing a few hundred samples should not be an insurmountable 
task.

Consider looking at the user guide for more informations on how to run the software. 

## Features

At the current time:
* a user can choose which serial port to take the data from.
* the application displays samples read from the A0 analog pin on the arduino.
* the application displays the samples fed in via serial port.
* the user can enhance details with a Y scaling slider.

## Dependencies
The project depends on the Java standard library and the swing suite of components. For the first version of the project, serial operations
are done with [the jSerialComm library](https://fazecast.github.io/jSerialComm/).
