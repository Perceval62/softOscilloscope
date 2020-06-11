package com.vincentperrier.softOscilloscope;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

import static com.fazecast.jSerialComm.SerialPort.LISTENING_EVENT_DATA_RECEIVED;

public class inputSerial implements input {

    SerialPort p;
    controller controller;
    String name;
    int baudRate;

    inputSerial(String portName, int baudRate, controller c) {
        try {
            this.p = SerialPort.getCommPort(portName);
            this.controller = c;
            this.baudRate = baudRate;
            this.name = name;
            this.p.setBaudRate(baudRate);
            p.addDataListener(new SerialPortDataListener() {
                @Override
                public int getListeningEvents() {
                    return LISTENING_EVENT_DATA_RECEIVED;
                }

                @Override
                public void serialEvent(SerialPortEvent event) {
                    byte[] tmp = event.getReceivedData();
                    float[] ret = new float[tmp.length];
                    for (int i = 0; i < tmp.length; i++) {
                        ret[i] = (float) tmp[i];
                    }
                    controller.treatIncomingSamples(ret);
                }
            });
            p.openPort();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loopRead() {
    }

    public inputSerial withBaudRate(int newBaud) {
        this.p.closePort();
        inputSerial ret = new inputSerial(this.name, this.baudRate, this.controller);
        ret.p = this.p;
        ret.baudRate = newBaud;
        ret.p.setBaudRate(newBaud);
        this.p.openPort(100);
        return ret;
    }
}
