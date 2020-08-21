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

import java.util.Vector;

public class controllerPacket extends abstractModel implements controller{

    Vector<modelPacket> channels = new Vector<>();


    public controllerPacket() {

    }

    public void pushPacket(Vector<modelPacket> incoming) {
        channels = incoming;
        this.notifyObservers();
    }

    public modelPacket getSamples(int channel) {
        if(channels.isEmpty() || channel > 3)
        {
            float buf[] = new float[100];
            return new modelPacket(channel, buf);
        }

        return channels.elementAt(channel);
    }
}
