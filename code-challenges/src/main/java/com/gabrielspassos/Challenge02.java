package com.gabrielspassos;

import java.util.ArrayList;
import java.util.List;

/*
Revert a List
revert([1,2,3,4,5]) -> [5,4,3,2,1]
*/
public class Challenge02 {

    public static List<Integer> revert(List<Integer> list) {
        var reverted = new ArrayList<Integer>();

        for (int i = list.size(); i > 0; i--) {
            reverted.add(list.get(i-1));
        }

        return reverted;
    }

}
