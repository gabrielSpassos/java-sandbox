package com.gabrielspassos.patterns.creational.abstractFactory;

public class LogisticService {

    private final TransportAbstractFactory transportAbstractFactory;

    public LogisticService (TransportAbstractFactory transportAbstractFactory) {
        this.transportAbstractFactory = transportAbstractFactory;
    }

    public boolean transport() {
        var transport = transportAbstractFactory.createTransport();
        return transport.deliver();
    }
}
