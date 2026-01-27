package com.gabrielspassos;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Challenge12Test {

    @Test
    void shouldSort() {
        var input = new ArrayList<>(List.of(5, 4, 3, 2, 1));
        var expected = List.of(1, 2, 3, 4, 5);

        var result = Challenge12.sort(input);

        assertEquals(expected, result);
    }

}