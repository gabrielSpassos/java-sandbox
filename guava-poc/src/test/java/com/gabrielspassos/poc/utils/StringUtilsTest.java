package com.gabrielspassos.poc.utils;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    private static StringUtils stringUtils;

    @BeforeAll
    static void setup() {
        stringUtils = new StringUtils();
    }

    @Test
    void shouldRemoveSpecialChars() {
        var input = "He#${;l`l~o?";

        String output = stringUtils.removeSpecialChars(input);

        assertEquals("Hello", output);
    }
}