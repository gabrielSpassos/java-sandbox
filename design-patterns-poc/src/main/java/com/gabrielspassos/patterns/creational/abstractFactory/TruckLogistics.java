package com.gabrielspassos.patterns.creational.abstractFactory;

public class TruckLogistics implements TransportFactory {

    @Override
    public Transport createTransport() {
        return new Truck();
    }

}
