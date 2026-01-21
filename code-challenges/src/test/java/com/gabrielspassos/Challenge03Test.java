package com.gabrielspassos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Challenge03Test {

    @Test
    void shouldFindByKey() {
        var result1 = Challenge03.lookupById("1");
        var result2 = Challenge03.lookupById("2");

        assertTrue(result1.isPresent());
        assertEquals("John", result1.get());

        assertTrue(result2.isPresent());
        assertEquals("Gabriel", result2.get());
    }

    @Test
    void shouldNotFindByKey() {
        var result = Challenge03.lookupById("3");

        assertTrue(result.isEmpty());
    }

    @Test
    void shouldFindByEmail() {
        var result1 = Challenge03.lookupByEmailOrName("john@john.jhon.com");
        var result2 = Challenge03.lookupByEmailOrName("gabriel@gabriel.gabriel.com");

        assertTrue(result1.isPresent());
        assertEquals("John", result1.get());

        assertTrue(result2.isPresent());
        assertEquals("Gabriel", result2.get());
    }

    @Test
    void shouldNotFindByEmail() {
        var result = Challenge03.lookupByEmailOrName("foo@foo.foo.com");

        assertTrue(result.isEmpty());
    }

    @Test
    void shouldFindByName() {
        var result1 = Challenge03.lookupByEmailOrName("John");
        var result2 = Challenge03.lookupByEmailOrName("Gabriel");

        assertTrue(result1.isPresent());
        assertEquals("john@john.jhon.com", result1.get());

        assertTrue(result2.isPresent());
        assertEquals("gabriel@gabriel.gabriel.com", result2.get());
    }

    @Test
    void shouldNotFindByName() {
        var result = Challenge03.lookupByEmailOrName("foo");

        assertTrue(result.isEmpty());
    }

}