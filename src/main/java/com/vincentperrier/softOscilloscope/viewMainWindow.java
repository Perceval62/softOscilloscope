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
    JTextField baudrateTextField;

    input sourceOfData;

    JButton applyButton;
    JButton pauseButton;

    JSlider YscalingSlider;

    viewMainWindow(controller c, input in) {
        this.sourceOfData = in;
        this.graph = new viewGraph(c);

        this.portNameTextField = new JTextField();
        this.portNameTextField.setPreferredSize(new Dimension(100, 40));
        this.baudrateTextField = new JTextField();
        this.baudrateTextField.setPreferredSize(new Dimension(100, 40));
        this.baudrateTextField.setText("9600");

        this.applyButton = new JButton("apply");
        this.applyButton.setPreferredSize(new Dimension(100, 40));

        this.pauseButton = new JButton("pause");
        this.pauseButton.setPreferredSize(new Dimension(100, 40));

        this.YscalingSlider = new JSlider();

        this.applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sourceOfData.setBaudRate(Integer.parseInt(baudrateTextField.getText()));
                sourceOfData.setName(portNameTextField.getText());
            }
        });

        this.pauseButton.addActionListener(new ActionListener() {
            String pausetext = new String("pause");
            String playText= new String("play");
            @Override
            public void actionPerformed(ActionEvent e) {

                if(pauseButton.getText().equals(pausetext))
                {
                    graph.pause(true);
                    pauseButton.setText(playText);

                }
                else if(pauseButton.getText().equals(playText))
                {
                    graph.pause(false);
                    pauseButton.setText(pausetext);

                }
            }
        });

        this.portNameTextField.addActionListener(e -> sourceOfData.setName(portNameTextField.getText()));

        this.baudrateTextField.addActionListener(e -> sourceOfData.setBaudRate(Integer.parseInt(baudrateTextField.getText())));

        this.setBounds(0,0 ,1100, 400);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Add stuff to the upper bar of the ui
        JPanel northPanel = new JPanel();
        JLabel serialNameLabel = new JLabel("Com port name:");
        serialNameLabel.setPreferredSize(new Dimension(100, 100));
        northPanel.add(new JLabel("Com port: "), BorderLayout.WEST);
        northPanel.add(this.portNameTextField, BorderLayout.CENTER);
        northPanel.add(new JLabel("Baud rate: "));
        northPanel.add(this.baudrateTextField);
        northPanel.add(this.applyButton, BorderLayout.EAST);
        northPanel.add(this.pauseButton);

        //Add stuff to the lower bar of the ui
        JPanel visualPane = new JPanel();
        visualPane.add(new JLabel("Scaling"));

        this.YscalingSlider.setMaximum(1);
        this.YscalingSlider.setMaximum(80);
        this.YscalingSlider.setValue(graph.getScaling());
        this.YscalingSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                graph.setScaling(YscalingSlider.getValue());
            }
        });
        visualPane.add(this.YscalingSlider);


        //Add everything to GUI
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
