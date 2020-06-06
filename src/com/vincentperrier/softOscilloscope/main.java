package com.vincentperrier.softOscilloscope;

/**
 * Main class of the soft oscilloscope project
 */
public class main{

    //Todo Initialize data model.
    public static packet initializeModel()
    {
        float array[] = new float[100];
        for(int i = 0; i < 100; i++)
        {
            array[i] = 0.0f;
        }
        return new packet(array);
    }

    //Todo Initialize data controller.

    //Todo Initialize data view.

    public static void main(String args[])
    {
        System.out.println("Starting the program");
        packet model = initializeModel();
        packetController controller = new packetController(model);
        windowFrame view = new windowFrame(controller);
        model.addObserver(view);
        input in = new input(controller);

        while(true) in.dummyLoopRead();
    }

}
