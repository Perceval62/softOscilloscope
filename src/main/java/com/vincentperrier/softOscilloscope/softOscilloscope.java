package com.vincentperrier.softOscilloscope;

/**
 * Main class of the soft oscilloscope project
 */
public class softOscilloscope {

    //Todo Initialize data model.
    public static modelPacket initializeModel() {
        float array[] = new float[100];
        for (int i = 0; i < 100; i++) {
            array[i] = 0.0f;
        }
        return new modelPacket(array);
    }

    //Todo Initialize data controller.

    //Todo Initialize data view.

    public static void main(String args[]) {
        System.out.println("Starting the program");
        modelPacket model = initializeModel();
        controllerPacket controller = new controllerPacket(model);
        viewMainWindow view = new viewMainWindow(controller);
        model.addObserver(view);
        inputDummy in = new inputDummy(controller);
        //inputSerial testSerial = new inputSerial("bruh", 9600, controller);

        Thread t1 = new Thread(() -> {
            while (true) in.loopRead();
        });
        t1.run();
    }

}
