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
package softOscilloscopeTest;

import com.vincentperrier.softOscilloscope.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class controllerPacketTest {

    float originalArray[] = {1.0f, 2.0f, 3.0f, 4.0f, 5.0f, 6.0f, 7.0f, 8.0f};

    controllerPacket testElement;
    modelPacket testData;

    @BeforeEach
    void setup()
    {
        testData = new modelPacket(originalArray);
        this.testElement = new controllerPacket(testData);
    }

    @Test
    void treatIncomingSamples() {
        try {
            Assertions.assertArrayEquals(originalArray, testElement.getSamples());

            float testArray[] = {8.0f, 7.0f, 6.0f, 5.0f, 4.0f, 3.0f, 2.0f, 1.0f, 0.0f};
            this.testElement.treatIncomingSamples(testArray);
            Assertions.assertNotEquals(originalArray, testElement.getSamples());
            Assertions.assertArrayEquals(testArray, testElement.getSamples());

            this.testElement.treatIncomingSamples(null);
            if(this.testElement.getSamples() != null)
            {
                fail("Null sample array not properly handled");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}