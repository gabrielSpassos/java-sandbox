package com.gabrielspassos.poc.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class StringUtilsTest {

    @Test
    void shouldCapitalizeString() {
        assertEquals("Test", StringUtils.capitalize("test"));
        assertEquals("TEST", StringUtils.capitalize("TEST"));
        assertEquals("TesT", StringUtils.capitalize("TesT"));
        assertEquals("LongTest", StringUtils.capitalize("longTest"));
        assertEquals("Shorttest", StringUtils.capitalize("shorttest"));
        assertEquals("A", StringUtils.capitalize("a"));
        assertEquals("A", StringUtils.capitalize("A"));
    }

    @Test
    void shouldReturnSameInput() {
        assertNull(StringUtils.capitalize(null));
        assertEquals("", StringUtils.capitalize(""));
    }

}