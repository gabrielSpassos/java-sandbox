package com.gabrielspassos.solution;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Solution {

    public List<Boolean> getResults(int[][] queries) {
        var results = new ArrayList<Boolean>();
        for (int[] query : queries) {
            var queryType = getQueryType(query);
            System.out.println(queryType);
        }

        return results;
    }

    private QueryType getQueryType(int[] query) {
        var type = query[0];
        return QueryType.getByValue(type);
    }

    private enum QueryType {
        ONE(1),
        TWO(2);

        private final int value;

        QueryType(int value) {
            this.value = value;
        }

        public static QueryType getByValue(int value) {
            return Stream.of(QueryType.values())
                    .filter(queryType -> queryType.value == value)
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Invalid query value"));
        }
    }

}
