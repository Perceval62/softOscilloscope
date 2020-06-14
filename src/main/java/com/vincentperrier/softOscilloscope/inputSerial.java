/*
    This file is part of softOscilloscope.

    softOscilloscope is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    softOscilloscope is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with softOscilloscope.  If not, see <https://www.gnu.org/licenses/>.
*/
package com.vincentperrier.softOscilloscope;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Vector;

import static com.fazecast.jSerialComm.SerialPort.LISTENING_EVENT_DATA_RECEIVED;

public class inputSerial implements input {

    SerialPort p;
    controller controller;
    String name;
    int baudRate;

    inputSerial(String portName, int baudRate, controller c) {
        try {
            System.out.println("building port");
            this.p = SerialPort.getCommPort(portName);
            this.controller = c;
            this.baudRate = baudRate;
            this.name = name;
            this.p.setBaudRate(baudRate);
            this.p.clearRTS();
            this.p.clearDTR();
            if(p.openPort())
            {
                System.out.println("Port opened");
            }
            else
            {
                System.out.println("Could not open the given com port");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loopRead() {
        while(this.p.bytesAvailable() < 1000);
        byte input[] = new byte[this.p.bytesAvailable()];

        this.p.readBytes(input, input.length);
        float packet[] = new float[input.length];
        for(int i = 0; i < input.length; i++)
        {
            packet[i] = input[i] / 10.0f;
            System.out.println(packet[i]);
        }
        this.controller.treatIncomingSamples(packet);
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
