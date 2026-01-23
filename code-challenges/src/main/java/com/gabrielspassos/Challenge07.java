package com.gabrielspassos;

import java.util.ArrayList;
import java.util.List;

/*
Create a function that can group a list of numbers by a given number.
group_by([1,2,3,4,5,6,7,8,9,10], 3) -> [[1,2,3], [4,5,6], [7,8,9], [10]]
Please make sure the same function works with strings as well.
group_by(["a","b","c","d","e","f","g","h","i","j"], 3) -> [["a","b","c"], ["d","e","f"], ["g","h","i"], ["j"]]
Can you refactor the code and create your own group by function, do not use any prebuild function.
*/
public class Challenge07 {

    public static <T> List<List<T>> groupBy(List<T> list, Integer groupByFactor) {
        var result = new ArrayList<List<T>>();
        var group = new ArrayList<T>();

        for (T item: list) {
            if (group.size() < groupByFactor) {
                group.add(item);
            } else {
                result.add(List.copyOf(group));
                group.clear();
                group.add(item);
            }
        }

        result.add(group);
        return result;
    }

}
