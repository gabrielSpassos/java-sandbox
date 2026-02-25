package com.gabrielspassos.patterns.structural.bridge;

public class RedColor implements Color {

    @Override
    public Color applyColor() {
        IO.println("red.");
        return this;
    }

}
