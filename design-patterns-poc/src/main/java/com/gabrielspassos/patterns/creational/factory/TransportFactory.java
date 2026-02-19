package com.gabrielspassos.patterns.creational.factory;

public class TransportFactory {

    public static Transport getTransport(String type) {
        if (null == type) {
            throw new IllegalArgumentException("invalid type");
        }

        return switch (type.toUpperCase()) {
            case "ROAD" -> new Truck();
            case "WATER" -> new Ship();
            default -> throw new IllegalArgumentException("No transport for this type");
        };
    }
}
