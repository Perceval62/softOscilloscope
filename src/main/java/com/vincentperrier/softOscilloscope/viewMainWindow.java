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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class viewMainWindow extends JFrame implements view {
    viewGraph graph;
    JTextField portNameTextField;
    input sourceOfData;
    JButton applyButton;
    JSlider slider;

    viewMainWindow(controller c, input in) {
        this.sourceOfData = in;
        this.graph = new viewGraph(c);
        this.portNameTextField = new JTextField();
        this.applyButton = new JButton("apply");
        this.applyButton.setPreferredSize(new Dimension(100, 20));
        this.slider = new JSlider();

        this.applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sourceOfData.setName(portNameTextField.getText());
            }
        });

        this.portNameTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sourceOfData.setName(portNameTextField.getText());
            }
        });

        this.setBounds(0,0 ,1100, 400);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel northPanel = new JPanel();
        JLabel serialNameLabel = new JLabel("Com port name:");
        serialNameLabel.setPreferredSize(new Dimension(100, 100));
        northPanel.add(new JLabel("Com port:"), BorderLayout.WEST);
        portNameTextField.setPreferredSize(new Dimension(100, 20));
        northPanel.add(portNameTextField, BorderLayout.CENTER);
        northPanel.add(this.applyButton, BorderLayout.EAST);

        JPanel visualPane = new JPanel();
        visualPane.add(new JLabel("Scaling"));
        this.slider.setMaximum(1);
        this.slider.setMaximum(80);
        this.slider.setValue(graph.getScaling());
        this.slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                graph.setScaling(slider.getValue());
            }
        });
        visualPane.add(this.slider);

        this.getContentPane().add(northPanel, BorderLayout.PAGE_START);
        this.getContentPane().add(visualPane, BorderLayout.PAGE_END);
        this.getContentPane().add(graph, BorderLayout.CENTER);
        this.setTitle("Soft Oscilloscope V 0.1");
        this.setVisible(true);
    }

    public void update() {
        this.graph.update();
    }
}
