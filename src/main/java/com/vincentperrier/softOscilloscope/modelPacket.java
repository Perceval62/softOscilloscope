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

public class modelPacket extends abstractModel {

    private float samplesBuffer[];

    public modelPacket(float array[]) {
        super();
        if(array != null)
        {
            this.samplesBuffer = array.clone();
        }
    }

    public float[] getPacket() {
        return samplesBuffer.clone();
    }

    public modelPacket withSamples(float array[]) {
        try {
            if (array != null) {
                modelPacket ret = new modelPacket(array);
                for (view iter : this.listOfViews) {
                    ret.addObserver(iter);
                }
                this.notifyObservers();
                return ret;
            } else {
                throw new Exception("Cannot set the internal buffer to a null reference");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
