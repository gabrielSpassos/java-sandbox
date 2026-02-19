package com.gabrielspassos.patterns.creational.abstractFactory;

public class ShipLogistics implements TransportAbstractFactory {

    @Override
    public Transport createTransport() {
        return new Ship();
    }

}
