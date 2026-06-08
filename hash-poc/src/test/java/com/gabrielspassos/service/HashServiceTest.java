package com.gabrielspassos.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashServiceTest {

    @Test
    void shouldHash() {
        HashService hashService = new HashService();

        assertEquals(890597889, hashService.hash("gabriel"));
    }

    @Test
    void shouldHandleNullOnHash() {
        HashService hashService = new HashService();

        assertEquals(0, hashService.hash(null));
    }

    @Test
    void shouldHandleEmptyOnHash() {
        HashService hashService = new HashService();

        assertEquals(0, hashService.hash(""));
    }

    @Test
    void shouldHandleBlankOnHash() {
        HashService hashService = new HashService();

        assertEquals(0, hashService.hash("   "));
    }

    @Test
    void shouldBasicHash() {
        HashService hashService = new HashService();

        assertEquals(-890597889, hashService.basicHash("gabriel"));
    }

    @Test
    void shouldHandleNullOnBasicHash() {
        HashService hashService = new HashService();

        assertEquals(-1, hashService.basicHash(null));
    }

    @Test
    void shouldHandleEmptyOnBasicHash() {
        HashService hashService = new HashService();

        assertEquals(-1, hashService.basicHash(""));
    }

    @Test
    void shouldHandleBlankOnBasicHash() {
        HashService hashService = new HashService();

        assertEquals(-1, hashService.basicHash("   "));
    }

    @Test
    void shouldDbj2Hash() {
        HashService hashService = new HashService();

        assertEquals(229466779428027L, hashService.djb2Hash("gabriel"));
    }

    @Test
    void shouldHandleNullAtDbj2Hash() {
        HashService hashService = new HashService();

        assertEquals(-1, hashService.djb2Hash(null));
    }

    @Test
    void shouldHandleEmptyAtDbj2Hash() {
        HashService hashService = new HashService();

        assertEquals(-1, hashService.djb2Hash(""));
    }

    @Test
    void shouldHandleBlankAtDbj2Hash() {
        HashService hashService = new HashService();

        assertEquals(-1, hashService.djb2Hash(" "));
    }

}