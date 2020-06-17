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

/**
 * Main class of the soft oscilloscope project
 */
public class softOscilloscope {

    //Initialize basic data
    public static modelPacket initializeModel() {
        float array[] = new float[100];
        for (int i = 0; i < 100; i++) {
            array[i] = 0.0f;
        }
        return new modelPacket(array);
    }

    //RunTest
    public static void runExample() {
        System.out.println("Starting the program");
        modelPacket model = initializeModel();
        controllerPacket controller = new controllerPacket(model);
        try {
            inputDummy in = new inputDummy(controller);
            viewMainWindow view = new viewMainWindow(controller, (input) in);
            model.addObserver(view);
            Thread t1 = new Thread(() -> {
                while (true) in.loopRead();
            });
            t1.run();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    //RunTest
    public static void runSerialExample() {
        System.out.println("Starting the program");
        modelPacket model = initializeModel();
        controllerPacket controller = new controllerPacket(model);
        try {
            inputSerial testSerial = new inputSerial("", 9600, controller);
            viewMainWindow view = new viewMainWindow(controller, testSerial);
            model.addObserver(view);
            while (true)
            {
                testSerial.loopRead();
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    //Todo Initialize data view.

    public static void main(String args[]) {
        try {
            //This line makes the SWING made UI look more like native apps.
            // e.g on Windows 10 the application will look native
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch (Exception e) {
            e.printStackTrace();
        }


        //softOscilloscope.runExample();
        runSerialExample();
    }

}
