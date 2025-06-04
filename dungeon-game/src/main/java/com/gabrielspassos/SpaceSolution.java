package com.gabrielspassos;


public class SpaceSolution {
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;

        // Only need one array of size n+1 to handle edge cases
        int[] dp = new int[n + 1];

        // Initialize dp array with max value (infinity substitute)
        for (int i = 0; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        dp[n - 1] = 1;  // The princess' cell "exit" requirement

        // Iterate from bottom to top, right to left
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int minHealthOnExit = Math.min(dp[j], dp[j + 1]);
                dp[j] = Math.max(1, minHealthOnExit - dungeon[i][j]);
            }
        }

        return dp[0];
    }

    public static void main(String[] args) {
        SpaceSolution game = new SpaceSolution();

        int[][] dungeon1 = {
                {-2, -3, 3},
                {-5, -10, 1},
                {10, 30, -5}
        };

        System.out.println("Minimum initial health: " + game.calculateMinimumHP(dungeon1));  // Output: 7

        int[][] dungeon2 = {
                {0}
        };

        System.out.println("Minimum initial health: " + game.calculateMinimumHP(dungeon2));  // Output: 1
    }
}

