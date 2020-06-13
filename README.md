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

This is what this project aims to achieve:
* Having a GUI interface to inspect a given waveform.
* WIP: Receiving samples from a serial port and maybe network in future iterations.
* WIP: Having an option to be able to export a waveform as a spreadsheet.

## Structure
This project is structured with a Model View Controller (MVC) architecture for easy scalability.
Here is the role of a few classes:
* modelPacket: stores the current version of the data that is presented by the application.
* controllerPacket: controls the values inside the packetModel and handles the
requets for updates by the views.
* viewGraph: wait for changes on the modelPacket and update the graph according to the info given by controllerPacker.
* inputSerial: open a serial port and reads it when data is available, pushes the data to the controller for it to be handled.

## Dependencies
The project depends on the Java standard library and the swing suite of components. For the first version of the project, serial operations
are done with [the jSerialComm library](https://fazecast.github.io/jSerialComm/).