package com.gabrielspassos;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class Challenge08Test {

    @Test
    void shouldTransformList() {
        Function<Integer, Integer> function = (x) -> x * 2;
        var list = List.of(1, 2, 3, 4, 5);
        var expected = List.of(2, 4, 6, 8, 10);

        var result = Challenge08.map(list, function);

        assertEquals(expected, result);
    }

}