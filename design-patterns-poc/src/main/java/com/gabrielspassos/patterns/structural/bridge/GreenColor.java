package com.gabrielspassos.patterns.structural.bridge;

public class GreenColor implements Color {

    @Override
    public Color applyColor() {
        IO.println("green.");
        return this;
    }

}
