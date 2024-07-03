package com.gabrielspassos;

import java.util.List;

/*
 These algorithms are for Bin Packing problems where items arrive one at a time (in unknown order),
 each must be put in a bin, before considering the next item.
*/
public class OnlineSolution {

    // When processing next item, check if it fits in the same bin as the last item. Use a new bin only if it does not.
    public Integer nextFit(List<Integer> weight, Integer binCapacity) {
        Integer result = 1;
        Integer binRemainingCapacity = binCapacity;

        // Place items one by one
        for (Integer integer : weight) {
            // If this item can't fit in current bin
            if (integer > binRemainingCapacity) {
                result++; // Use new bin
                binRemainingCapacity = binCapacity - integer;
            } else {
                binRemainingCapacity -= integer;
            }
        }

        return result;
    }

    /*
       When processing the next item, scan the previous bins in order and place the item in the first bin that fits.
       Start a new bin only if it does not fit in any of the existing bins
     */
    public Integer firstFit(List<Integer> weight, Integer binCapacity) {
        Integer result = 1;
        return result;
    }

}
