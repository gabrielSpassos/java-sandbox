package com.gabrielspassos.patterns.structural.composite;

public class Triangle implements Shape {

    @Override
    public boolean draw(String color) {
        IO.println("Draw triangle with color " + color);
        return true;
    }

}
