package com.gabrielspassos.solution;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionTest {

    @Test
    public void shouldReturnExample1() {
        var queries = new int[][]{{1, 2}, {2, 3, 3}, {2, 3, 1}, {2, 2, 2}};
        var expected = List.of(false, true, true);
        var solution = new Solution();

        var results = solution.getResults(queries);

        assertEquals(expected, results);
    }

    @Test
    public void shouldReturnExample2() {
        var queries = new int[][]{{1, 7}, {2, 7, 6}, {1, 2}, {2, 7, 5}, {2, 7, 6}};
        var expected = List.of(true, true, false);
        var solution = new Solution();

        var results = solution.getResults(queries);

        assertEquals(expected, results);
    }

}