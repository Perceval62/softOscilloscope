package softOscilloscopeTest;

import com.vincentperrier.softOscilloscope.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions;

import java.lang.reflect.Executable;

class inputDummyTest {

    controller testController = new controller() {
        @Override
        public void treatIncomingSamples(float[] input) {

        }

        @Override
        public float[] getSamples() {
            return new float[0];
        }
    };

    @Test
    void loopRead() {
        try {
            /* instantiate with a null reference */
            Assertions.assertThrows(Exception.class, () -> {
                inputDummy nullTest = new inputDummy(null);
            });

            /* Launched directly */
            inputDummy directTest = new inputDummy(this.testController);
            //Launch by direct call,
            directTest.loopRead();

            /* Launched in a thread */
            inputDummy threadTest = new inputDummy(this.testController);
            //launch by thread
            Thread t1 = new Thread(() -> {threadTest.loopRead();});
            t1.run();

            /* Split between thread */
            inputDummy sharedTest = new inputDummy(this.testController);
            //launch by thread
            Thread t2 = new Thread(() -> {sharedTest.loopRead();});
            t1.run();
            //Launch by direct call,
            sharedTest .loopRead();

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}