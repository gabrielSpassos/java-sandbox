package com.gabrielspassos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class LuhnCheckTest {

    @Test
    public void testNullCardNumber() {
        assertFalse(LuhnCheck.isValid(null));
    }

    @Test
    public void testEmptyCardNumber() {
        assertFalse(LuhnCheck.isValid(""));
    }

    @Test
    public void testBlankCardNumber() {
        assertFalse(LuhnCheck.isValid("   "));
    }

    @Test
    public void testValidCardNumber() {
        assertTrue(LuhnCheck.isValid("17893729974"));
    }

    @Test
    public void testValidCardNumber2() {
        assertTrue(LuhnCheck.isValid("79927398713"));
    }

    @Test
    public void testInvalidCardNumber() {
        assertFalse(LuhnCheck.isValid("17893729975"));
    }
}