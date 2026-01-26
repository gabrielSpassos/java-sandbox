package com.gabrielspassos;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/*
Create a function that can map a function execution to each element of a list, return a new list.

map([1,2,3,4,5], (x) => x * 2) -> [2,4,6,8,10]

The map function should receive a collection(list or array) and a function that will be apply to each element of the collection.

Now refactor the code and do not use any prebuild function.
*/
public class Challenge08 {

    public static <T> List<T> map(List<T> list, Function<T, T> function) {
        var result = new ArrayList<T>();
        list.forEach(item -> {
            var operationResult = function.apply(item);
            result.add(operationResult);
        });
        return result;
    }

}
