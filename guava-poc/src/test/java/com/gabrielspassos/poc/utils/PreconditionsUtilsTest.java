package com.gabrielspassos.poc.utils;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PreconditionsUtilsTest {

    private static PreconditionsUtils preconditionsUtils;

    @BeforeAll
    public static void setup() {
        preconditionsUtils = new PreconditionsUtils();
    }

    @Test
    void shouldThrowErrorForInvalidIntegerInput() {
        var input = -1;

        IllegalArgumentException exception = assertThrowsExactly(IllegalArgumentException.class,
                () -> preconditionsUtils.checkIntegerInputBiggerThanZero(input));

        assertEquals("Invalid input: -1. Input must be bigger than zero.", exception.getMessage());
    }

    @Test
    void shouldValidateIntegerInput() {
        var input = 1;

        assertDoesNotThrow(() -> preconditionsUtils.checkIntegerInputBiggerThanZero(input));
    }
}