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
    public static void runExample()
    {
        System.out.println("Starting the program");
        modelPacket model = initializeModel();
        controllerPacket controller = new controllerPacket(model);
        viewMainWindow view = new viewMainWindow(controller);
        model.addObserver(view);
        try {
            inputDummy in = new inputDummy(controller);
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
    public static void runSerialExample()
    {
        System.out.println("Starting the program");
        modelPacket model = initializeModel();
        controllerPacket controller = new controllerPacket(model);
        viewMainWindow view = new viewMainWindow(controller);
        model.addObserver(view);
        try {
            inputSerial testSerial = new inputSerial("COM6", 9600, controller);
            testSerial.loopRead();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    //Todo Initialize data view.

    public static void main(String args[]) {
        //softOscilloscope.runExample();
        runSerialExample();
    }

}
