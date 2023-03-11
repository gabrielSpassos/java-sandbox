package com.gabrielspassos.poc.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PreconditionsServiceTest {

    private static PreconditionsService preconditionsService;

    @BeforeAll
    public static void setup() {
        preconditionsService = new PreconditionsService();
    }

    @Test
    void shouldThrowErrorForInvalidIntegerInput() {
        var input = -1;

        IllegalArgumentException exception = assertThrowsExactly(IllegalArgumentException.class,
                () -> preconditionsService.checkIntegerInputBiggerThanZero(input));

        assertEquals("Invalid input: -1. Input must be bigger than zero.", exception.getMessage());
    }

    @Test
    void shouldValidateIntegerInput() {
        var input = 1;

        assertDoesNotThrow(() -> preconditionsService.checkIntegerInputBiggerThanZero(input));
    }
}