package com.gabrielspassos;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OfflineSolutionTest {

    @Test
    void shouldReturn2ForFirstFitDecreasing() {
        List<Integer> weights = Arrays.asList(4, 8, 1, 4, 2, 1);
        Integer binCapacity = 10;
        var offlineSolution = new OfflineSolution();

        Integer necessaryBins = offlineSolution.firstFitDecreasing(weights, binCapacity);

        assertEquals(2, necessaryBins);
    }

    @Test
    void shouldReturn3ForFirstFitDecreasing() {
        List<Integer> weights = Arrays.asList(2, 5, 4, 7, 1, 3, 8);
        Integer binCapacity = 10;
        var offlineSolution = new OfflineSolution();

        Integer necessaryBins = offlineSolution.firstFitDecreasing(weights, binCapacity);

        assertEquals(3, necessaryBins);
    }

    @Test
    void shouldReturn4ForFirstFitDecreasing() {
        List<Integer> weights = Arrays.asList(9, 8, 2, 2, 5, 4);
        Integer binCapacity = 10;
        var offlineSolution = new OfflineSolution();

        Integer necessaryBins = offlineSolution.firstFitDecreasing(weights, binCapacity);

        assertEquals(4, necessaryBins);
    }

}