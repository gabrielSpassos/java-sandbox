package com.gabrielspassos.patterns.structural.decorator;

public class SportsCar extends CarDecorator {

    public SportsCar(Car car) {
        super(car);
    }

    @Override
    public String assemble() {
        var assembled = super.assemble();
        IO.print("Adding feature of sports car. ");
        return "Sports Car | " + assembled;
    }
}
