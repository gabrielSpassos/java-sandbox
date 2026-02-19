package com.gabrielspassos.patterns.creational.abstractFactory;

public class Truck implements Transport {

    @Override
    public boolean deliver() {
        IO.println("truck delivery");
        return true;
    }
}
