package com.gabrielspassos.patterns.creational.abstractFactory;

public class ShipLogistics implements TransportFactory {

    @Override
    public Transport createTransport() {
        return new Ship();
    }

}
