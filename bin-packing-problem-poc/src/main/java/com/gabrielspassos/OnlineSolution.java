package com.gabrielspassos;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
 These algorithms are for Bin Packing problems where items arrive one at a time (in unknown order),
 each must be put in a bin, before considering the next item.
*/
public class OnlineSolution {

    /*
      When processing next item, check if it fits in the same bin as the last item. Use a new bin only if it does not.
    */
    public Integer nextFit(List<Integer> weights, Integer binCapacity) {
        int result = 0;
        Integer binRemainingCapacity = binCapacity;

        // Place items one by one
        for (Integer weight : weights) {
            // If this item can't fit in current bin
            if (weight > binRemainingCapacity) {
                result++; // Use new bin
                binRemainingCapacity = binCapacity - weight;
            } else {
                binRemainingCapacity -= weight;
            }
        }

        return result + 1;
    }

    /*
       When processing the next item, scan the previous bins in order and place the item in the first bin that fits.
       Start a new bin only if it does not fit in any of the existing bins
    */
    public Integer firstFit(List<Integer> weights, Integer binCapacity) {
        int result = 0;

        // Create an array to store remaining space in bins there can be at most n bins
        // Initialize it with 0
        List<Integer> binRemainingCapacityList = IntStream.of(new int[weights.size()]).boxed().collect(Collectors.toList());

        // Place items one by one
        for (Integer weight : weights) {
            int j;
            for (j = 0; j < result; j++) {
                if (binRemainingCapacityList.get(j) >= weight) {
                    binRemainingCapacityList.set(j, binRemainingCapacityList.get(j) - weight);
                    break;
                }
            }

            // If no bin could accommodate weight[i]
            if (j == result) {
                binRemainingCapacityList.set(result, binCapacity - weight);
                result++;
            }
        }

        return result;
    }

    /*
      The idea is to places the next item in the *tightest* spot.
      That is, put it in the bin so that the smallest empty space is left.
    */
    public Integer bestFit(List<Integer> weights, Integer binCapacity) {
        int result = 0;

        // Create an array to store remaining space in bins there can be at most n bins
        // Initialize it with 0
        List<Integer> binRemainingCapacityList = IntStream.of(new int[weights.size()]).boxed().collect(Collectors.toList());

        for (Integer weight : weights) {
            int j;
            int minSpaceLeft = binCapacity + 1;
            int bestBinIndex = 0;

            for (j = 0; j < result; j++) {
                if (binRemainingCapacityList.get(j) >= weight
                        && binRemainingCapacityList.get(j) - weight < minSpaceLeft) {
                    bestBinIndex = j;
                    minSpaceLeft = binRemainingCapacityList.get(j) - weight;
                }
            }

            // If no bin could accommodate weight[i] create a new bin
            if (minSpaceLeft == binCapacity + 1) {
                binRemainingCapacityList.set(result, binCapacity - weight);
                result++;
            } else {
                // Assign the item to best bin
                binRemainingCapacityList.set(bestBinIndex, binRemainingCapacityList.get(bestBinIndex) - weight);
            }
        }

        return result;
    }

    /*
      The idea is to places the next item in the least tight spot to even out the bins.
      That is, put it in the bin so that most empty space is left.
    */
    public Integer worstFit(List<Integer> weights, Integer binCapacity) {
        int result = 0;

        // Create an array to store remaining space in bins there can be at most n bins
        // Initialize it with 0
        List<Integer> binRemainingCapacityList = IntStream.of(new int[weights.size()]).boxed().collect(Collectors.toList());

        for (Integer weight : weights) {
            int j;
            int maxSpaceLeft = -1;
            int worstBinIndex = 0;

            for (j = 0; j < result; j++) {
                if (binRemainingCapacityList.get(j) >= weight
                        && binRemainingCapacityList.get(j) - weight > maxSpaceLeft) {
                    worstBinIndex = j;
                    maxSpaceLeft = binRemainingCapacityList.get(j) - weight;
                }
            }

            // If no bin could accommodate weight[i] create a new bin
            if (maxSpaceLeft == -1) {
                binRemainingCapacityList.set(result, binCapacity - weight);
                result++;
            } else {
                // Assign the item to worst bin
                binRemainingCapacityList.set(worstBinIndex, binRemainingCapacityList.get(worstBinIndex) - weight);
            }
        }

        return result;
    }

}
