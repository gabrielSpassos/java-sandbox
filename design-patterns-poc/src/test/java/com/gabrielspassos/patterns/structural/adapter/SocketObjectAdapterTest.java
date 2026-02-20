package com.gabrielspassos.patterns.structural.adapter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SocketObjectAdapterTest {

    @Test
    void shouldReturn220Volts() {
        var socketObjectAdapter = new SocketObjectAdapter();

        var volt = socketObjectAdapter.get220Volt();

        assertNotNull(volt);
        assertEquals(220, volt.getVolts());
    }

    @Test
    void shouldReturn120Volts() {
        var socketObjectAdapter = new SocketObjectAdapter();

        var volt = socketObjectAdapter.get120Volt();

        assertNotNull(volt);
        assertEquals(120, volt.getVolts());
    }

    @Test
    void shouldReturn12Volts() {
        var socketObjectAdapter = new SocketObjectAdapter();

        var volt = socketObjectAdapter.get12Volt();

        assertNotNull(volt);
        assertEquals(12, volt.getVolts());
    }

}