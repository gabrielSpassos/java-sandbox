package com.gabrielspassos.solution;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Solution {

    private static final int START = 0;

    public List<Boolean> getResults(int[][] queries) {
        var results = new ArrayList<Boolean>();
        var obstacles = new ArrayList<Integer>();
        for (int[] query : queries) {
            var queryType = getQueryType(query);
            System.out.println("Query type: " + queryType);

            if (QueryType.ONE == queryType) {
                obstacles.add(query[1]);
                obstacles.sort(Integer::compareTo);
                System.out.println("Obstacles: " + obstacles);
            } else {
                var maxLimit = query[1];
                var size = query[2];
                System.out.println("Max limit: " + maxLimit);
                System.out.println("Size: " + size);

                var isPossible = isPossibleToPlaceBlock(obstacles, maxLimit, size);
                results.add(isPossible);
            }
        }

        return results;
    }

    private Boolean isPossibleToPlaceBlock(ArrayList<Integer> obstacles, int maxLimit, int size) {
        var distances = new ArrayList<Integer>();
        for (int i = 0; i < obstacles.size(); i++) {
            var distance = START == i ? obstacles.get(i) - START : obstacles.get(i) - obstacles.get(i - 1);
            distances.add(distance);
        }

        return distances.stream().anyMatch(distance -> size <= distance);
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
