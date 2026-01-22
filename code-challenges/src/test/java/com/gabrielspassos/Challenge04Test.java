package com.gabrielspassos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Challenge04Test {

    @Test
    void shouldReturnEnglish() {
        assertEquals("English", Challenge04.patternMatcher("Usa"));
    }

    @Test
    void shouldReturnPortuguese() {
        assertEquals("Portuguese", Challenge04.patternMatcher("Brazil"));
    }

    @Test
    void shouldReturnSpanish() {
        assertEquals("Spanish", Challenge04.patternMatcher("Spain"));
    }

    @Test
    void shouldReturnItalian() {
        assertEquals("Italian", Challenge04.patternMatcher("Italy"));
    }

    @Test
    void shouldReturnFrench() {
        assertEquals("French", Challenge04.patternMatcher("France"));
    }

    @Test
    void shouldThrowError() {
        assertThrows(IllegalArgumentException.class, () -> Challenge04.patternMatcher("Argentina"));
    }

}