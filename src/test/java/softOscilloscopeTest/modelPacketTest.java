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

import com.vincentperrier.softOscilloscope.modelPacket;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class modelPacketTest {
    float zeroArray[] = {0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
    float originalArray[] = {1.0f, 2.0f, 3.0f, 4.0f, 5.0f, 6.0f, 7.0f, 8.0f};
    float testArray[] = {8.0f, 7.0f, 6.0f, 5.0f, 4.0f, 3.0f, 2.0f, 1.0f, 0.0f};
    float invalidArray[] = null;
    int wrongTypeArray[] = {1, 2, 3, 4, 5};

    @Test
    void getPacket() {
        modelPacket test1 = new modelPacket(originalArray);
        Assertions.assertArrayEquals(originalArray, test1.getPacket());

        modelPacket test2 = new modelPacket(testArray);
        Assertions.assertArrayEquals(testArray, test2.getPacket());

        assertThrows(Exception.class, () -> {
            modelPacket test3 = new modelPacket(invalidArray);
            Assertions.assertArrayEquals(invalidArray, test3.getPacket());
            Assertions.assertNotNull(test3.getPacket());
        });
    }

    @Test
    void withSamples() {
        modelPacket test = new modelPacket(zeroArray);
        Assertions.assertArrayEquals(zeroArray, test.getPacket());

        test = test.withSamples(originalArray);
        Assertions.assertArrayEquals(originalArray, test.getPacket());

        test = test.withSamples(testArray);
        Assertions.assertArrayEquals(testArray, test.getPacket());

        final modelPacket cpy = test;
        Assertions.assertThrows(Exception.class, () -> {
            modelPacket tmp = cpy.withSamples(invalidArray);
            if(tmp.getPacket() == null)
            {
                fail();
            }
        });
    }
}