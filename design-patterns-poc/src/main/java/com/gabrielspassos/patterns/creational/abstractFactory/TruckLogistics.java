package com.gabrielspassos.patterns.creational.abstractFactory;

public class TruckLogistics implements TransportAbstractFactory {

    @Override
    public Transport createTransport() {
        return new Truck();
    }

}
