package com.gabrielspassos.poc.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ThrowableServiceTest {

    private static ThrowableService throwableService;

    @BeforeAll
    public static void setup() {
        throwableService = new ThrowableService();
    }

    @Test
    void shouldThrowIllegalArgumentException() {
        var throwableExample = new Throwable("Throwable example");

        IllegalArgumentException exception = assertThrowsExactly(IllegalArgumentException.class,
                () -> throwableService.throwSomeError(throwableExample));

        assertEquals(throwableExample, exception.getCause());
    }

    @Test
    void shouldPropagateException() {
        var throwableExample = new IllegalStateException("IllegalStateException example");

        IllegalStateException exception = assertThrowsExactly(IllegalStateException.class,
                () -> throwableService.throwSomeError(throwableExample));

        assertEquals("IllegalStateException example", exception.getMessage());
    }

}