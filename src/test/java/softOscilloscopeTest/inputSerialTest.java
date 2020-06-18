package softOscilloscopeTest;


import com.vincentperrier.softOscilloscope.*;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Executable;

import static org.junit.jupiter.api.Assertions.*;

class inputSerialTest {

    @Test
    void setName() {
        controller dummyController = new controller() {
            @Override
            public void treatIncomingSamples(float[] input) {

            }

            @Override
            public float[] getSamples() {
                return new float[0];
            }
        };

        String emptyName = new String("");
        int defaultBaudRate = 9600;
        //An empty name should be valid
        inputSerial testSubject = new inputSerial(emptyName, defaultBaudRate, dummyController);

        //A name that doesnt contain either COM[X] or dev/ttyXYZ should be invalid
        String invalidName = "tralalalalalalalallfalllal";
        testSubject.setName(invalidName);
        assertNotEquals(invalidName, testSubject.getName());

        //A name shouldn't contain spaces
        invalidName = "COM 5";
        testSubject.setName(invalidName);
        assertNotEquals(invalidName, testSubject.getName());

        //A null reference is invalid
        //A name shouldn't contain spaces
        final String nullRef = null;
        assertThrows(Exception.class, () -> testSubject.setName(nullRef));

        String validName = "COM5";
        testSubject.setName(validName);
        assertEquals(validName, testSubject.getName());

        validName = "/dev/ttyACM0";
        testSubject.setName(validName);
        assertEquals(validName, testSubject.getName());
    }

    @Test
    void setBaudRate() {
        //A negative baud rate is invalid

        //A baud rate of 0 is invalid

        //A null reference to a number is invalid
    }
}