package com.gabrielspassos.patterns.creational.factory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransportFactoryTest {

    @Test
    void shouldGetTruckTransport() {
        var transport = TransportFactory.getTransport("road");

        assertInstanceOf(Truck.class, transport);
        assertTrue(transport.deliver());
    }

    @Test
    void shouldGetShipTransport() {
        var transport = TransportFactory.getTransport("WATER");

        assertInstanceOf(Ship.class, transport);
        assertTrue(transport.deliver());
    }

    @Test
    void shouldThrowErrorWithNullType() {
        assertThrows(IllegalArgumentException.class, () -> TransportFactory.getTransport(null));
    }

    @Test
    void shouldThrowErrorWithNonConfiguredType() {
        assertThrows(IllegalArgumentException.class, () -> TransportFactory.getTransport("AIR"));
    }

}