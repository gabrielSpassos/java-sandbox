package com.gabrielspassos.challenge16;

public class Mosquito {

    private static final int MOVE_COUNT_MIN_TO_REPRODUCE = 5;

    private int moveCount;
    private Pair<Integer, Integer> position;

    public Mosquito() {
        this.moveCount = 0;
    }

    public int[][] move(int[][] board) {
        moveCount++;
        return board;
    }

    public boolean shouldReproduce() {
        return MOVE_COUNT_MIN_TO_REPRODUCE <= moveCount; //todo: check mosquito nearby
    }
}
