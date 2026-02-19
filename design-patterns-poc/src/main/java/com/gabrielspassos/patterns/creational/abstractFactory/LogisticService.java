package com.gabrielspassos.patterns.creational.abstractFactory;

public class LogisticService {

    private final TransportFactory transportFactory;

    public LogisticService (TransportFactory transportFactory) {
        this.transportFactory = transportFactory;
    }

    public boolean transport() {
        var transport = transportFactory.createTransport();
        return transport.deliver();
    }
}
