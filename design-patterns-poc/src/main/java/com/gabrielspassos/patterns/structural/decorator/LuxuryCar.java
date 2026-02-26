package com.gabrielspassos.patterns.structural.decorator;

public class LuxuryCar extends CarDecorator {

    public LuxuryCar(Car car) {
        super(car);
    }

    @Override
    public String assemble() {
        var assembledCar = super.assemble();
        IO.print("Adding feature of luxury car. ");
        return "Luxury Car | " + assembledCar;
    }
}
