package com.vincentperrier.softOscilloscope;

import javax.swing.*;

public class viewMainWindow extends JFrame implements view {
    viewGraph g;

    viewMainWindow(controller c)
    {
        this.g = new viewGraph(c);
        this.setVisible(true);
        this.setSize(400, 400);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.add(g);
    }

    public void update()
    {
        this.g.update();
    }
}
