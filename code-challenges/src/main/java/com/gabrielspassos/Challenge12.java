package com.gabrielspassos;

import java.util.List;

/*
Create a function that can sort a list of numbers. The implementation should be a Bubble Sort(good to learn but terrible to use in production).

bubble_sort([5,4,3,2,1]) -> [1,2,3,4,5]

The sort function should receive a collection(list or array) and return a new sorted list.
*/
public class Challenge12 {

    public static List<Integer> sort(List<Integer> unsortedList) {
        for (int i = 0; i < unsortedList.size() - 1; i++) {
            for (int j = 0; j < unsortedList.size() - 1; j++) {
                if (unsortedList.get(j) > unsortedList.get(j + 1)) {
                    var temp = unsortedList.get(j);
                    unsortedList.set(j, unsortedList.get(j + 1));
                    unsortedList.set(j + 1, temp);
                }
            }
        }

        return unsortedList;
    }
}
