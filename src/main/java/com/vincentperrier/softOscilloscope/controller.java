package com.vincentperrier.softOscilloscope;

public interface controller {
    public void treatIncomingSamples(float input[]);

    public float[] getSamples();
}
