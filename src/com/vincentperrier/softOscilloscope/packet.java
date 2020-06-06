package com.vincentperrier.softOscilloscope;

import java.util.Vector;

public class packet extends observable {

    private float samplesBuffer[];

    public packet(float array[])
    {
        super();
        this.samplesBuffer = array;
    }

    public float[] getPacket()
    {
        return samplesBuffer.clone();
    }

    public packet withSamples(float array[])
    {
        try{
            if(array != null)
            {
                packet ret = new packet(array);
                for(observer iter: this.listOfObservers)
                {
                    ret.addObserver(iter);
                }
                this.notifyObservers();
                return ret;
            }else {
                throw new Exception("Cannot set the internal buffer to a null reference");
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
