package com.gabrielspassos.patterns.creational.factory;

public class ShipLogistics extends LogisticService {

    @Override
    Transport createTransport() {
        return new Ship();
    }

}
