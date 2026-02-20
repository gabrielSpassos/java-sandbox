package com.gabrielspassos.patterns.structural.composite;

public class Circle implements Shape {

    @Override
    public boolean draw(String color) {
        IO.println("Draw circle with color " + color);
        return true;
    }

}
