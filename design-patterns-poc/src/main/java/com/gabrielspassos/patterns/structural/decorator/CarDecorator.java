package com.gabrielspassos.patterns.structural.decorator;

public class CarDecorator implements Car {

    private final Car car;

    public CarDecorator(Car car) {
        this.car = car;
    }

    @Override
    public String assemble() {
        return this.car.assemble();
    }
}
