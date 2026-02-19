package com.gabrielspassos.patterns.creational.abstractFactory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AbstractFactoryTest {

    @Test
    void shouldTransportViaTruck() {
        var truckLogistics = new TruckLogistics();
        var logisticsService = new LogisticService(truckLogistics);

        assertTrue(logisticsService.transport());
    }

    @Test
    void shouldTransportViaShip() {
        var shipLogistics = new ShipLogistics();
        var logisticsService = new LogisticService(shipLogistics);

        assertTrue(logisticsService.transport());
    }

}