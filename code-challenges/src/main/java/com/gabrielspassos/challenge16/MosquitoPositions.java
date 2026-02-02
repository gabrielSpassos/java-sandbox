package com.gabrielspassos.challenge16;

public record MosquitoPositions<R, C>(
        Position<R, C> upPosition,
        Position<R, C> downPosition,
        Position<R, C> leftPosition,
        Position<R, C> rightPosition,
        Position<R, C> upDiagonalLeft,
        Position<R, C> upDiagonalRight,
        Position<R, C> downDiagonalLeft,
        Position<R, C> downDiagonalRight
) {

    public Position<R, C>[] asArray() {
        Position<R, C>[] arr = new Position[]{
                upPosition, downPosition, leftPosition, rightPosition,
                upDiagonalLeft, upDiagonalRight, downDiagonalLeft, downDiagonalRight
        };

        return arr;
    }
}