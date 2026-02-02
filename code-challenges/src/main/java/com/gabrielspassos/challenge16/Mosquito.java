package com.gabrielspassos.challenge16;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class Mosquito implements Character{

    private static final int MOVE_COUNT_MIN_TO_REPRODUCE = 5;

    private int moveCount;
    private Position<Integer, Integer> position;

    public Mosquito(Position<Integer, Integer> position) {
        this.moveCount = 0;
        this.position = position;
    }

    @Override
    public Character[][] move(Character[][] board) {
        this.moveCount++;
        var currentPosition = position;

        if (shouldReproduce(board)) {
            var newMosquito = new Mosquito(currentPosition);
        }

        var mosquitoPossiblePositions = getMosquitoPossiblePositions(board).asArray();
        var newPossiblePosition = mosquitoPossiblePositions[ThreadLocalRandom.current().nextInt(mosquitoPossiblePositions.length)];
        var x = board[newPossiblePosition.row()][newPossiblePosition.column()];
        //todo: check positions
        return board;
    }

    private boolean shouldReproduce(Character[][] board) {
        if (MOVE_COUNT_MIN_TO_REPRODUCE > moveCount) {
            return false;
        }

        var mosquitosPosition = getMosquitoPossiblePositions(board);

        return isOtherMosquitoAtPosition(mosquitosPosition.upPosition(), board)
                || isOtherMosquitoAtPosition(mosquitosPosition.downPosition(), board)
                || isOtherMosquitoAtPosition(mosquitosPosition.leftPosition(), board)
                || isOtherMosquitoAtPosition(mosquitosPosition.rightPosition(), board)
                || isOtherMosquitoAtPosition(mosquitosPosition.upDiagonalLeft(), board)
                || isOtherMosquitoAtPosition(mosquitosPosition.upDiagonalRight(), board)
                || isOtherMosquitoAtPosition(mosquitosPosition.downDiagonalLeft(), board)
                || isOtherMosquitoAtPosition(mosquitosPosition.downDiagonalRight(), board);
    }

    private boolean isOtherMosquitoAtPosition(Position<Integer, Integer> position, Character[][] board) {
        return Optional.ofNullable(board[position.column()][position.row()])
                .filter(character -> character instanceof Mosquito)
                .filter(mosquito -> !mosquito.equals(this))
                .isPresent();
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

    private MosquitoPositions<Integer, Integer> getMosquitoPossiblePositions(Character[][] board) {
        var currentPosition = position;

        var upRowNumber = currentPosition.row() > 0 ? currentPosition.row() - 1 : currentPosition.row();
        var downRowNumber = currentPosition.row() < board.length ? currentPosition.row() + 1 : currentPosition.row();
        var leftColumnNumber = currentPosition.column() > 0 ? currentPosition.column() - 1 : currentPosition.column();
        var rightColumnNumber = currentPosition.column() < board.length ? currentPosition.column() + 1 : currentPosition.column();

        var upPosition = new Position<>(upRowNumber, currentPosition.column());
        var downPosition = new Position<>(downRowNumber, currentPosition.column());
        var leftPosition = new Position<>(currentPosition.row(), leftColumnNumber);
        var rightPosition = new Position<>(currentPosition.row(), rightColumnNumber);
        var upDiagonalLeft = new Position<>(upRowNumber, leftColumnNumber);
        var upDiagonalRight = new Position<>(upRowNumber, rightColumnNumber);
        var downDiagonalLeft = new Position<>(downRowNumber, leftColumnNumber);
        var downDiagonalRight = new Position<>(downRowNumber, rightColumnNumber);

        return new MosquitoPositions<>(
                upPosition,
                downPosition,
                leftPosition,
                rightPosition,
                upDiagonalLeft,
                upDiagonalRight,
                downDiagonalLeft,
                downDiagonalRight
        );
    }
}
