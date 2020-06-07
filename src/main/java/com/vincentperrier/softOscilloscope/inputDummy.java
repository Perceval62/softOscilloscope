package com.vincentperrier.softOscilloscope;

public class inputDummy {
    controller dataDestionation;
    public inputDummy(controller c)
    {
        this.dataDestionation = c;
    }

    public void loopRead()
    {
        for(int i = 0; i < 5; i++)
        {
            try {
            Thread.sleep(1000);
            float array[] = new float[100];
            for(int x = 0; x < 100; x++)
            {
                array[x] = (float)  Math.sin(x);
                array[x] = array[x] * 10 * i;
            }
            this.dataDestionation.treatIncomingSamples(array);
            System.out.println("looping");
            }
            catch (Exception e){
                   e.printStackTrace();
            }
        }
    }
}
