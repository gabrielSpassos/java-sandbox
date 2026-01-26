package com.gabrielspassos;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BinaryOperator;

/*
Create a function that can reduce a list to a single value.

reduce([1,2,3,4,5], (acc, x) => acc + x, 0) -> 15

The reduce function should receive a collection(list or array), a function that will be apply to each element of the collection and an initial value.

Now refactor the code and do not use any prebuild function.
*/
public class Challenge10 {

    public static <T> T reduce(List<T> list, BinaryOperator<T> function, T initialValue) {
        T accumulator = initialValue;
        
        for (T item: list) {
            accumulator = function.apply(accumulator, item);
        }

        return accumulator;
    }

}
