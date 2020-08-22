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

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.util.Vector;

public class viewGraph extends JPanel implements view {

    controller controller;
    int scaling = 10;
    boolean isPaused = false;

    Vector<modelPacket> channels = new Vector<>();

    public void pause(boolean newState)
    {
        this.isPaused = newState;
    }
    public boolean isPaused()
    {
        return this.isPaused;
    }

    public viewGraph(controller c) {
        try {
            if (c != null) {
                this.controller = c;
                this.setSize(300, 300);
            } else {
                throw new Exception("Cannot assign null reference to object");
            }

            float buf[] = new float[100];
            for(int i = 0; i < 4; i++) {
                channels.add(new modelPacket(0, buf));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setScaling(int newValue)
    {
        this.scaling = newValue;
    }
    public int getScaling(){
        return this.scaling;
    }

    public void update() {
        this.repaint();
        if(isPaused == false) {
            for (int i = 0; i < 4; i++) {
                channels.set(i, controller.getSamples(i));
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int padding = 20;
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.getWidth() - 1, this.getHeight() - 1);
        g.setColor(Color.RED);
        g.drawLine(1, (this.getHeight() / 2), this.getWidth()-1, (this.getHeight() / 2));
        g.drawLine(padding, 1, padding, this.getHeight()-1);

        for(int i = 0; i < 4; i++) {
            paintLine(channels.elementAt(i), g);
        }
    }

    public void simpleDumpToCsv()
    {
        try {
            FileWriter dumpfile = new FileWriter("dump.csv");

            float buf1[] = channels.elementAt(0).getPacket();
            float buf2[] = channels.elementAt(1).getPacket();
            float buf3[] = channels.elementAt(2).getPacket();
            float buf4[] = channels.elementAt(3).getPacket();

            for(int i = 0; i < 1000; i++)
            {
                dumpfile.write(buf1[i] + "," + buf2[i] + "," + buf3[i] + "," + buf4[i] + "\n");
            }

            dumpfile.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    void paintLine(modelPacket channel, Graphics g)
    {
        int padding = 20;

        int yOffset = (this.getHeight() / 2);
        int inter = this.getWidth()/channel.getPacket().length;
        int previousX = padding;
        int previousY = (int) -channel.getPacket()[0]*scaling + yOffset;

        int currentX = 0;
        int currentY = 0;

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(getChannelColor(channel.getSource()));
        g2.setStroke(new BasicStroke(3));
        //PaintComponent use reverse coordinates so we need to reverse the samples
        //signs
        float[] amplifiedBuffer = new float[channel.getPacket().length];
        for (int i = 300; i < channel.getPacket().length; i++) {
            amplifiedBuffer[i] = -((channel.getPacket())[i] * scaling);
            currentX = (i - 300) + padding;
            currentY = ((int) amplifiedBuffer[i] + (this.getHeight() / 2));
            g2.drawLine(previousX, previousY, currentX, currentY);
            previousX = currentX;
            previousY = currentY;
        }
    }

    public static Color getChannelColor(int channel)
    {
        Color ret = Color.BLACK;
        switch (channel)
        {
            case 0:
                ret = Color.GREEN;
                break;
            case 1:
                ret =  Color.BLUE;
                break;
            case 2:
                ret = Color.ORANGE;
                break;
            case 3:
                ret = Color.PINK;
                break;
            default:
                ret = Color.BLACK;
                break;
        }
        return ret;
    }
}
