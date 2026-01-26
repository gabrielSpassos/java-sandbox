package com.gabrielspassos;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.BinaryOperator;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Challenge10Test {

    @Test
    void shouldReduce() {
        var list = List.of(1, 2, 3, 4, 5);
        BinaryOperator<Integer> function = (acc, x) -> acc + x;

        var result = Challenge10.reduce(list, function, 0);

        assertEquals(15, result);
    }

}