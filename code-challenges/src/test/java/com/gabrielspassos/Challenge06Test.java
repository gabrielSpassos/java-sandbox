package com.gabrielspassos;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Challenge06Test {

    @Test
    void tokenizeByComma() {
        var expected = List.of("Hello", "World", "How", "Are", "You");

        var result = Challenge06.tokenize("Hello,World,How,Are,You", ",");

        assertEquals(expected, result);
    }

    @Test
    void tokenizeBySpace() {
        var expected = List.of("Hello", "World", "How", "Are", "You");

        var result = Challenge06.tokenize("Hello World How Are You", " ");

        assertEquals(expected, result);
    }

    @Test
    void tokenizeByDash() {
        var expected = List.of("Hello", "World", "How", "Are", "You");

        var result = Challenge06.tokenize("Hello-World-How-Are-You", "-");

        assertEquals(expected, result);
    }
}