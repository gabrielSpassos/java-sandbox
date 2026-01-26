package com.gabrielspassos;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Challenge09Test {

    @Test
    void shouldFilter() {
        var list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Predicate<Integer> filterCriteria = (x) -> x % 2 == 0;
        var expected = List.of(2, 4, 6, 8, 10);

        var result = Challenge09.filter(list, filterCriteria);

        assertEquals(expected, result);
    }

}