package com.gabrielspassos.patterns.creational.factory;

public abstract class LogisticService {

    abstract Transport createTransport();

    public boolean transport() {
        var transport = createTransport();
        return transport.deliver();
    }
}
