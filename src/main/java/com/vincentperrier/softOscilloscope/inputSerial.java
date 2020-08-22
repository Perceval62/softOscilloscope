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
    boolean shouldReset = false;

    public void setName(String name)
    {
        if( !name.contains("COM") && !name.contains("dev/tty"))
        {
            name = "";
        }
        if(name.contains(" "))
        {
            name = "";
        }
        try {
            this.name = name;
            reinitialize();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void setBaudRate(int rate)
    {
        try {
            this.baudRate = rate;
            reinitialize();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public String getName()
    {
        String ret = new String (name);
        return ret;
    }

    public int getBaudRate()
    {
        int ret = this.baudRate;
        return ret;
    }

    private void reinitialize() throws Exception
    {
        if(this.p != null) {
            this.p.closePort();
        }
        this.initialize();
    }

    private void initialize() throws Exception
    {
        this.p = SerialPort.getCommPort(this.name);
        this.baudRate = baudRate;
        this.name = name;
        this.p.setBaudRate(baudRate);
        this.p.clearRTS();
        this.p.clearDTR();
        if(p.openPort())
        {
            System.out.println("Port opened");
        }
    }

    public inputSerial(String portName, int baudRate, controller c) {
        try{
            this.name = portName;
            this.baudRate = baudRate;
            this.controller = c;
            initialize();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loopRead() {
        try {
            if (this.p.isOpen() == false) {
                //Get out of the function after a delay to not waste cpu cycles
                Thread.sleep(1000);
                return;
            }

            Vector<modelPacket> ret = new Vector<>(4);
            for(int channel = 0; channel < 4; channel++)
            {
                byte arr[] = new byte[1000];
                float buf[] = new float[1000];
                byte c[] = {(byte) ('0' + channel)};
                p.writeBytes(c, 1);

                while(p.bytesAvailable() < 1000) {
                    Thread.sleep(10);
                }
                if (p.readBytes(arr, arr.length) == -1)
                {
                    return;
                }

                for(int i = 0; i < 1000; i++)
                {
                    buf[i] = (arr[i] / 10.0f);
                }

                modelPacket out = new modelPacket(channel, buf);
                ret.add(out);
            }
            controller.pushPacket(ret);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
