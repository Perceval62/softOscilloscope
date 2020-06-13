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