package com.gabrielspassos;

import java.util.Comparator;
import java.util.List;

/*
 These algorithms are for Bin Packing problems where we have all items upfront each must be put in a bin,
*/
public class OfflineSolution {

    /*
     Returns number of bins required using first fit decreasing offline algorithm
    */
    public Integer firstFitDecreasing(List<Integer> weights, Integer binCapacity) {
        var onlineSolution = new OnlineSolution();

        // First sort all weights in decreasing order
        weights.sort(Comparator.reverseOrder());

        return onlineSolution.firstFit(weights, binCapacity);
    }
}
