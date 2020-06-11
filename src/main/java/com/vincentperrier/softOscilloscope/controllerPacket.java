package com.vincentperrier.softOscilloscope;

public class controllerPacket implements controller {

    modelPacket model;

    controllerPacket(modelPacket e) {
        this.model = e;
    }

    public void treatIncomingSamples(float input[]) {
        model = model.withSamples(input);
    }

    public float[] getSamples() {
        return this.model.getPacket();
    }

}
