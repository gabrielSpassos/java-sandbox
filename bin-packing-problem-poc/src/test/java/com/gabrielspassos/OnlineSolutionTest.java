package com.gabrielspassos;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OnlineSolutionTest {

    @Test
    void shouldReturn3ForNextFit() {
        List<Integer> weights = Arrays.asList(4, 8, 1, 4, 2, 1);
        Integer binCapacity = 10;
        var onlineSolution = new OnlineSolution();

        Integer necessaryBins = onlineSolution.nextFit(weights, binCapacity);

        assertEquals(3, necessaryBins);
    }

    @Test
    void shouldReturn4ForNextFit() {
        List<Integer> weights = Arrays.asList(9, 8, 2, 2, 5, 4);
        Integer binCapacity = 10;
        var onlineSolution = new OnlineSolution();

        Integer necessaryBins = onlineSolution.nextFit(weights, binCapacity);

        assertEquals(4, necessaryBins);
    }

    @Test
    void shouldReturn5ForNextFit() {
        List<Integer> weights = Arrays.asList(2, 5, 4, 7, 1, 3, 8);
        Integer binCapacity = 10;
        var onlineSolution = new OnlineSolution();

        Integer necessaryBins = onlineSolution.nextFit(weights, binCapacity);

        assertEquals(5, necessaryBins);
    }

    @Test
    void shouldReturn2ForFirstFit() {
        List<Integer> weights = Arrays.asList(4, 8, 1, 4, 2, 1);
        Integer binCapacity = 10;
        var onlineSolution = new OnlineSolution();

        Integer necessaryBins = onlineSolution.firstFit(weights, binCapacity);

        assertEquals(2, necessaryBins);
    }

    @Test
    void shouldReturn4ForFirstFit() {
        List<Integer> weights = Arrays.asList(2, 5, 4, 7, 1, 3, 8);
        Integer binCapacity = 10;
        var onlineSolution = new OnlineSolution();

        Integer necessaryBins = onlineSolution.firstFit(weights, binCapacity);

        assertEquals(4, necessaryBins);
    }

    @Test
    void shouldAlsoReturn4ForFirstFit() {
        List<Integer> weights = Arrays.asList(9, 8, 2, 2, 5, 4);
        Integer binCapacity = 10;
        var onlineSolution = new OnlineSolution();

        Integer necessaryBins = onlineSolution.firstFit(weights, binCapacity);

        assertEquals(4, necessaryBins);
    }

    @Test
    void shouldReturn2ForBestFit() {
        List<Integer> weights = Arrays.asList(4, 8, 1, 4, 2, 1);
        Integer binCapacity = 10;
        var onlineSolution = new OnlineSolution();

        Integer necessaryBins = onlineSolution.bestFit(weights, binCapacity);

        assertEquals(2, necessaryBins);
    }

    @Test
    void shouldReturn4ForBestFit() {
        List<Integer> weights = Arrays.asList(2, 5, 4, 7, 1, 3, 8);
        Integer binCapacity = 10;
        var onlineSolution = new OnlineSolution();

        Integer necessaryBins = onlineSolution.bestFit(weights, binCapacity);

        assertEquals(4, necessaryBins);
    }

    @Test
    void shouldAlsoReturn4ForBestFit() {
        List<Integer> weights = Arrays.asList(9, 8, 2, 2, 5, 4);
        Integer binCapacity = 10;
        var onlineSolution = new OnlineSolution();

        Integer necessaryBins = onlineSolution.bestFit(weights, binCapacity);

        assertEquals(4, necessaryBins);
    }

    @Test
    void shouldReturn2ForWorstFit() {
        List<Integer> weights = Arrays.asList(4, 8, 1, 4, 2, 1);
        Integer binCapacity = 10;
        var onlineSolution = new OnlineSolution();

        Integer necessaryBins = onlineSolution.worstFit(weights, binCapacity);

        assertEquals(2, necessaryBins);
    }

    @Test
    void shouldReturn4ForWorstFit() {
        List<Integer> weights = Arrays.asList(2, 5, 4, 7, 1, 3, 8);
        Integer binCapacity = 10;
        var onlineSolution = new OnlineSolution();

        Integer necessaryBins = onlineSolution.worstFit(weights, binCapacity);

        assertEquals(4, necessaryBins);
    }

    @Test
    void shouldAlsoReturn4ForWorstFit() {
        List<Integer> weights = Arrays.asList(9, 8, 2, 2, 5, 4);
        Integer binCapacity = 10;
        var onlineSolution = new OnlineSolution();

        Integer necessaryBins = onlineSolution.worstFit(weights, binCapacity);

        assertEquals(4, necessaryBins);
    }

}