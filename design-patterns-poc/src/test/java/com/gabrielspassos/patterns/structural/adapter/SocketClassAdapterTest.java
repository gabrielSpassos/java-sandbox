package com.gabrielspassos.patterns.structural.adapter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SocketClassAdapterTest {

    @Test
    void shouldReturn220Volts() {
        var socketClassAdapter = new SocketClassAdapter();

        var volt = socketClassAdapter.get220Volt();

        assertNotNull(volt);
        assertEquals(220, volt.getVolts());
    }

    @Test
    void shouldReturn120Volts() {
        var socketClassAdapter = new SocketClassAdapter();

        var volt = socketClassAdapter.get120Volt();

        assertNotNull(volt);
        assertEquals(120, volt.getVolts());
    }

    @Test
    void shouldReturn12Volts() {
        var socketClassAdapter = new SocketClassAdapter();

        var volt = socketClassAdapter.get12Volt();

        assertNotNull(volt);
        assertEquals(12, volt.getVolts());
    }

}