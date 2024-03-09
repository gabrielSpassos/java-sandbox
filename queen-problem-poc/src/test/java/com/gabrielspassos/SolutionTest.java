package com.gabrielspassos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SolutionTest {

    @Test
    void shouldSolveQueensProblem() {
        var nQueensCount = 4;
        var solution = new Solution();

        String[][] result = solution.solveNQueensProblem(nQueensCount);

        assertNotNull(result);
        assertEquals(nQueensCount, result.length);
        assertEquals(".", result[0][0]);
        assertEquals(".", result[0][1]);
        assertEquals("Q", result[0][2]);
        assertEquals(".", result[0][3]);

        assertEquals("Q", result[1][0]);
        assertEquals(".", result[1][1]);
        assertEquals(".", result[1][2]);
        assertEquals(".", result[1][3]);

        assertEquals(".", result[2][0]);
        assertEquals(".", result[2][1]);
        assertEquals(".", result[2][2]);
        assertEquals("Q", result[2][3]);

        assertEquals(".", result[3][0]);
        assertEquals("Q", result[3][1]);
        assertEquals(".", result[3][2]);
        assertEquals(".", result[3][3]);
    }

}