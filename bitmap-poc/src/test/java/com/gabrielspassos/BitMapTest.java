package com.gabrielspassos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BitMapTest {

    @Test
    void shouldSetABit() {
        var bitMap = new BitMap(10);

        bitMap.set(4);

        assertTrue(bitMap.get(4));
    }

    @Test
    void shouldNotSetABit() {
        var bitMap = new BitMap(10);

        assertFalse(bitMap.get(4));
    }

    @Test
    void shouldClearBit() {
        var bitMap = new BitMap(10);

        bitMap.set(4);

        assertTrue(bitMap.get(4));

        bitMap.clear(4);

        assertFalse(bitMap.get(4));
    }

    @Test
    void shouldToggleBit() {
        var bitMap = new BitMap(10);

        bitMap.toggle(4);

        assertTrue(bitMap.get(4));
    }

    @Test
    void shouldToggleAlreadySetBit() {
        var bitMap = new BitMap(10);

        bitMap.set(4);
        assertTrue(bitMap.get(4));

        bitMap.toggle(4);
        assertFalse(bitMap.get(4));
    }

    @Test
    void shouldCleanAll() {
        var bitMap = new BitMap(10);

        bitMap.set(4);
        bitMap.set(6);
        bitMap.set(8);

        assertTrue(bitMap.get(4));
        assertTrue(bitMap.get(6));
        assertTrue(bitMap.get(8));

        bitMap.clearAll();

        assertFalse(bitMap.get(4));
        assertFalse(bitMap.get(6));
        assertFalse(bitMap.get(8));
    }

    @Test
    void shouldReturnCardinality() {
        var bitMap = new BitMap(10);

        bitMap.set(4);
        bitMap.set(6);
        bitMap.set(8);

        assertTrue(bitMap.get(4));
        assertTrue(bitMap.get(6));
        assertTrue(bitMap.get(8));

        var cardinality = bitMap.cardinality();

        assertEquals(3, cardinality);
    }

}