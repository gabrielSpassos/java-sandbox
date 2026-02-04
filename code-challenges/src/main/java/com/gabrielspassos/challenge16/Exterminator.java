package com.gabrielspassos.challenge16;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Exterminator implements Character {

    private static final int MAX = 99;
    private int direction = 1;
    private Position<Integer, Integer> position;

    public Exterminator(Position<Integer, Integer> position) {
        this.position = position;
    }

    @Override
    public Pair<List<Character>, Character[][]> move(Character[][] board) {
        var current = position;

        // 1 — remove from current cell
        board[current.column()][current.row()] = null;

        int nextRow = current.row() + direction;
        int nextCol = current.column() + direction;

        // 2 — reverse at borders
        if (nextRow > MAX || nextCol > MAX) {
            direction = -1;
            nextRow = current.row() + direction;
            nextCol = current.column() + direction;
        }

        if (nextRow < 0 || nextCol < 0) {
            direction = 1;
            nextRow = current.row() + direction;
            nextCol = current.column() + direction;
        }

        var nextPosition = new Position<>(nextRow, nextCol);

        // 3 — kill mosquito if present
        var list = new ArrayList<>();
        var optionalMosquito = getMosquitoAtPosition(nextPosition, board);
        if (optionalMosquito.isPresent()) {
            board[nextPosition.column()][nextPosition.row()] = null;
            list.add(optionalMosquito.get());
        }

        // 4 — place exterminator
        position = nextPosition;
        board[nextPosition.column()][nextPosition.row()] = this;

        return new Pair(list, board);
    }

    public Position<Integer, Integer> getPosition() {
        return position;
    }

    private Optional<Mosquito> getMosquitoAtPosition(Position<Integer, Integer> position, Character[][] board) {
        return Optional.ofNullable(board[position.column()][position.row()])
                .filter(character -> character instanceof Mosquito)
                .map(character -> (Mosquito) character);
    }

}
