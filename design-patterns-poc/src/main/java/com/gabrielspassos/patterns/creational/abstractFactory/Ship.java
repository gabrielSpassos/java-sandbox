package com.gabrielspassos.patterns.creational.abstractFactory;

public class Ship implements Transport {

    @Override
    public boolean deliver() {
        IO.println("Ship delivery");
        return true;
    }
}
