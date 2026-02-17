package com.gabrielspassos.patterns.creational.factory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FactoryTest {

    @Test
    void shouldTransportViaTruck() {
        var truckLogistics = new TruckLogistics();

        assertTrue(truckLogistics.transport());
    }

    @Test
    void shouldTransportViaShip() {
        var shipLogistics = new ShipLogistics();

        assertTrue(shipLogistics.transport());
    }

}