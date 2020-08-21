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
    float[] lastUnpausedPacket = new float[0];

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
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int padding = 20;
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, this.getWidth() - 1, this.getHeight() - 1);
        g.setColor(Color.RED);
        g.drawLine(1, (this.getHeight() / 2), this.getWidth()-1, (this.getHeight() / 2));
        g.drawLine(padding, 1, padding, this.getHeight()-1);

        for(int i = 0; i < 4; i++) {
            paintLine(controller.getSamples(i), g);
        }
    }

    public void simpleDumpToCsv()
    {
        try {
            FileWriter dumpfile = new FileWriter("dump.csv");
            for(float iter: this.lastUnpausedPacket)
            {
                dumpfile.write(String.valueOf(iter) + "\n");
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
        int previousY = (int) channel.getPacket()[0] + yOffset;

        int currentX = 0;
        int currentY = 0;

        g.setColor(getChannelColor(channel.getSource()));

        //PaintComponent use reverse coordinates so we need to reverse the samples
        //signs
        float[] amplifiedBuffer = new float[channel.getPacket().length];
        for (int i = 1; i < channel.getPacket().length; i++) {
            amplifiedBuffer[i] = (channel.getPacket())[i] * scaling;
            currentX = i*inter + padding;
            currentY = ((int) amplifiedBuffer[i] + (this.getHeight() / 2));
            g.drawLine(previousX, previousY, currentX, currentY);
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
