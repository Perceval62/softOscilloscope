package com.vincentperrier.softOscilloscope;

import javax.swing.*;
import java.awt.*;

public class windowFrame extends JFrame implements observer {
    graphView g;

    windowFrame(controller c)
    {
        this.g = new graphView(c);
        this.setVisible(true);
        this.setSize(400, 400);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.add(g);
    }

    public void update()
    {
        System.out.println("JFrame called back : D");
        this.g.update();
    }
}
