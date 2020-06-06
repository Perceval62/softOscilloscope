package com.vincentperrier.softOscilloscope;

import javax.swing.*;
import java.awt.*;

public class graphView extends JPanel implements observer{
    controller controller;
    public graphView(controller c)
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
        System.out.println("graphview called back : D");
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        float [] buf = this.controller.getSamples();

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
