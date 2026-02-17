package com.gabrielspassos.patterns.creational.factory;

public class TruckLogistics extends LogisticService {

    @Override
    Transport createTransport() {
        return new Truck();
    }

}
