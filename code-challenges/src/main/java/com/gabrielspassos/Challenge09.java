package com.gabrielspassos;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/*
Create a function that can filter a list based on a given condition.

filter([1,2,3,4,5,6,7,8,9,10], (x) => x % 2 == 0) -> [2,4,6,8,10]

The filter function should receive a collection(list or array) and a function that will be apply to each element of the collection.

Now refactor the code and do not use any prebuild function.
*/
public class Challenge09 {

    public static <T> List<T> filter(List<T> list, Predicate<T> filter) {
        var result = new ArrayList<T>();
        list.forEach(item -> {
            var isValidByFilter = filter.test(item);
            if (isValidByFilter) {
                result.add(item);
            }
        });
        return result;
    }

}
