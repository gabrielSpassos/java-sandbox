package com.gabrielspassos.patterns.structural.bridge;

public class Triangle extends Shape {

    public Triangle(Color color) {
        super(color);
    }

    @Override
    public Color applyColor() {
        IO.print("Triangle filled with color ");
        return color.applyColor();
    }
}
