package com.gabrielspassos.poc.utils;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ThrowableUtilsTest {

    private static ThrowableUtils throwableUtils;

    @BeforeAll
    public static void setup() {
        throwableUtils = new ThrowableUtils();
    }

    @Test
    void shouldThrowIllegalArgumentException() {
        var throwableExample = new Throwable("Throwable example");

        IllegalArgumentException exception = assertThrowsExactly(IllegalArgumentException.class,
                () -> throwableUtils.throwSomeError(throwableExample));

        assertEquals(throwableExample, exception.getCause());
    }

    @Test
    void shouldPropagateException() {
        var throwableExample = new IllegalStateException("IllegalStateException example");

        IllegalStateException exception = assertThrowsExactly(IllegalStateException.class,
                () -> throwableUtils.throwSomeError(throwableExample));

        assertEquals("IllegalStateException example", exception.getMessage());
    }

}