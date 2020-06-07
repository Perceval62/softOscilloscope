package com.vincentperrier.softOscilloscope;

import java.util.Vector;

public interface controller {
    public void treatIncomingSamples(float input[]);
    public float [] getSamples();
}
