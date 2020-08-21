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

    //RunTest
    public static void runSerialExample() {
        try {
            controllerPacket controller = new controllerPacket();
            inputSerial testSerial = new inputSerial("", 115200, controller);
            viewMainWindow view = new viewMainWindow(controller, testSerial);
            controller.addObserver(view);
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
