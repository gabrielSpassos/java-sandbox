package com.gabrielspassos;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Challenge07Test {

    @Test
    void shouldGroupByIntegers() {
        var expected = List.of(List.of(1, 2, 3), List.of(4, 5, 6), List.of(7, 8, 9), List.of(10));

        var result = Challenge07.groupBy(List.of(1,2,3,4,5,6,7,8,9,10), 3);

        assertEquals(expected, result);
    }

    @Test
    void shouldGroupByString() {
        var expected = List.of(List.of("a", "b", "c"), List.of("d", "e", "f"), List.of("g", "h", "i"), List.of("j", "k"));

        var result = Challenge07.groupBy(List.of("a","b","c","d","e","f","g","h","i","j","k"), 3);

        assertEquals(expected, result);
    }

}