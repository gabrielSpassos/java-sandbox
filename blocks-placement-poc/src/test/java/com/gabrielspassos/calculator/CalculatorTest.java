package com.gabrielspassos.calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void sum() {
        Calculator calculator = new Calculator();
        int result = calculator.sum(1, 2);
        assertEquals(3, result);
    }

}