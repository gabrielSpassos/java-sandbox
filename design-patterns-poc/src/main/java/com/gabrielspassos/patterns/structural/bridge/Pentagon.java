package com.gabrielspassos.patterns.structural.bridge;

public class Pentagon extends Shape {

    public Pentagon(Color color) {
        super(color);
    }

    @Override
    public Color applyColor() {
        IO.print("Pentagon filled with color ");
        return color.applyColor();
    }

}
