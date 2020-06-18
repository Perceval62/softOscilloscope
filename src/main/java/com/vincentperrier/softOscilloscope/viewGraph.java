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

public class viewGraph extends JPanel implements view {
    controller controller;
    int scaling = 10;
    boolean isPaused = false;

    float[] lastUnpausedPacket = new float[0];

    public void pause(boolean newState)
    {
        this.isPaused = newState;
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

        //If the user paused the graph, return
        float[] buf;
        if(this.isPaused == false)
        {
            buf = this.controller.getSamples();
            lastUnpausedPacket = buf;
        }
        else
        {
            buf = lastUnpausedPacket;
        }

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, this.getWidth() - 1, this.getHeight() - 1);

        int padding = 20;

        int yOffset = (this.getHeight() / 2);
        int inter = this.getWidth() / (buf.length + padding);
        int previousX = padding;
        int previousY = (int) buf[0] + yOffset;

        int currentX = 0;
        int currentY = 0;

        g.setColor(Color.RED);
        g.drawLine(1, (this.getHeight() / 2), this.getWidth()-1, (this.getHeight() / 2));
        g.drawLine(padding, 1, padding, this.getHeight()-1);
        g.setColor(Color.BLACK);
        //PaintComponent use reverse coordinates so we need to reverse the samples
        //signs

        float[] amplifiedBuffer = new float[buf.length];
        for (int i = 1; i < buf.length; i++) {
            amplifiedBuffer[i] = -buf[i] * scaling;
            currentX = (i * inter) + padding;
            currentY = ((int) amplifiedBuffer[i] + (this.getHeight() / 2));
            g.drawLine(previousX, previousY, currentX, currentY);
            previousX = currentX;
            previousY = currentY;
        }
    }
}
