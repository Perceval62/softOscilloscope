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

public class inputDummy {
    controller dataDestionation;

    public inputDummy(controller c) throws Exception
    {
        if(c != null)
        {
            this.dataDestionation = c;
        }
        else
        {
            throw new Exception("Cannot assign a null value as a controller");
        }
    }

    public void loopRead() {
        for (int i = 0; i < 7; i++) {
            try {
                Thread.sleep(100);
                float array[] = new float[1000];
                for (int x = 0; x < 1000; x++) {
                    float work = (2.0f * (float)Math.PI);
                    array[x] = (float) Math.sin(Math.toRadians(work * x/i));
                    array[x] = array[x] * 10 * i;
                }
                this.dataDestionation.treatIncomingSamples(array);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setName(String name)
    {

    }

    public void setBaudRate(int rate)
    {

    }
}
