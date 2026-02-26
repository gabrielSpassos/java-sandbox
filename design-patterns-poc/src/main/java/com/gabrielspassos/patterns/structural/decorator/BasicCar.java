package com.gabrielspassos.patterns.structural.decorator;

public class BasicCar implements Car {

    @Override
    public String assemble() {
        IO.print("Basic Car. ");
        return "Basic Car";
    }

}
