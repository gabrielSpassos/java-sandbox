package com.gabrielspassos.challenge16;

import java.util.Objects;

public class Mosquito {

    private static final int MOVE_COUNT_MIN_TO_REPRODUCE = 5;

    private int moveCount;
    private Position<Integer, Integer> position;

    public Mosquito() {
        this.moveCount = 0;
    }

    public int[][] move(int[][] board) {
        moveCount++;
        var currentPosition = position;

        return board;
    }

    private boolean shouldReproduce(int[][] board) {
        var currentPosition = position;

        var upRowNumber = currentPosition.row() > 0 ? currentPosition.row() - 1 : currentPosition.row();
        var downRowNumber = currentPosition.row() < board.length ? currentPosition.row() + 1 : currentPosition.row();

        var leftColumnNumber = currentPosition.column() > 0 ? currentPosition.column() - 1 : currentPosition.column();
        var rightColumnNumber = currentPosition.column() < board.length ? currentPosition.column() + 1 : currentPosition.column();

        var upPosition = new Position<>(upRowNumber, currentPosition.column());
        var downPosition = new Position<>(downRowNumber, currentPosition.column());
        var leftPosition = new Position<>(currentPosition.row(), leftColumnNumber);
        var rightPosition = new Position<>(currentPosition.row(), rightColumnNumber);


        return MOVE_COUNT_MIN_TO_REPRODUCE <= moveCount;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Mosquito mosquito = (Mosquito) o;
        return moveCount == mosquito.moveCount && Objects.equals(position, mosquito.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(moveCount, position);
    }
}
