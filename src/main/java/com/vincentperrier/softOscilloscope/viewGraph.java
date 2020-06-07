package com.vincentperrier.softOscilloscope;

import javax.swing.*;
import java.awt.*;

public class viewGraph extends JPanel implements view {
    controller controller;
    public viewGraph(controller c)
    {
        try
        {
            if(c != null)
            {
                this.controller = c;
                this.setSize(300, 300);
            }else{
                throw new Exception("Cannot assign null reference to object");
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void update()
    {
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        float [] buf = this.controller.getSamples();

        g.drawRect(0,0, this.getWidth(), this.getHeight());

        int padding = 40;
        int yOffset = (this.getHeight() / 2);
        int inter = this.getWidth() / buf.length;
        int previousX = padding;
        int previousY = (int)buf[0] + yOffset;
        int currentX = 0;
        int currentY = 0;

        for(int i = 1; i < buf.length; i++)
        {
            currentX = i * inter + padding;
            currentY = (int)buf[i] + (this.getHeight() / 2);
            g.drawLine(previousX, previousY, currentX, currentY);
            previousX = currentX;
            previousY = currentY;
        }

    }

}
