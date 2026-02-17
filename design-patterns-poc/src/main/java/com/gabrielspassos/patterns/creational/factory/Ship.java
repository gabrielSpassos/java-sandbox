package com.gabrielspassos.patterns.creational.factory;

public class Ship implements Transport {

    @Override
    public boolean deliver() {
        IO.println("Ship delivery");
        return true;
    }
}
