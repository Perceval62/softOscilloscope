package com.vincentperrier.softOscilloscope;

public class modelPacket extends model {

    private float samplesBuffer[];

    public modelPacket(float array[])
    {
        super();
        this.samplesBuffer = array;
    }

    public float[] getPacket()
    {
        return samplesBuffer.clone();
    }

    public modelPacket withSamples(float array[])
    {
        try{
            if(array != null)
            {
                modelPacket ret = new modelPacket(array);
                for(view iter: this.listOfViews)
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
