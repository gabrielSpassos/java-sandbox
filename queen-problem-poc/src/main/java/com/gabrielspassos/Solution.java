package com.gabrielspassos;

public class Solution {

    private static final String QUEEN = "Q";

    public String[][] solveNQueensProblem(int nQueensCount) {
        String[][] matrix = new String[nQueensCount][nQueensCount];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = ".";
            }
        }

        solveQueensProblem(matrix, 0, nQueensCount);
        return matrix;
    }

    private Boolean solveQueensProblem(String[][] matrix, int column, int nQueensCount) {
        if (column >= nQueensCount) {
            return true;
        }

        // Consider this column and try placing
        // this queen in all rows one by one
        for (int i = 0; i < matrix.length; i++) {

            // Check if the queen can be placed on
            // board[i][col]
            if (isSafe(matrix, i, column)) {

                // Place this queen in board[i][col]
                matrix[i][column] = QUEEN;

                // Recur to place rest of the queens
                if (solveQueensProblem(matrix, column + 1, nQueensCount)) {
                    return true;
                }

                // If placing queen in board[i][col]
                // doesn't lead to a solution then
                // remove queen from board[i][col]
                matrix[i][column] = "."; // BACKTRACK
            }
        }
        return false;
    }

    private boolean isSafe(String[][] matrix, int row, int column) {
        // Check this row on left side
        for (int i = 0; i < column; i++) {
            if (QUEEN.equals(matrix[row][i])) {
                return false;
            }
        }

        // Check upper diagonal on left side
        for (int i = row, j = column; i >= 0 && j >= 0; i--, j--) {
            if (QUEEN.equals(matrix[i][j])) {
                return false;
            }
        }

        // Check lower diagonal on left side
        for (int i = row, j = column; j >= 0 && i < matrix.length; i++, j--) {
            if (QUEEN.equals(matrix[i][j])) {
                return false;
            }
        }

        return true;
    }
}
