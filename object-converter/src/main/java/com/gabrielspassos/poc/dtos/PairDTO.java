package com.gabrielspassos.poc.dtos;

public class PairDTO<L, R> {

    private final L left;

    private final R right;

    public PairDTO(L left, R right) {
        this.left = left;
        this.right = right;
    }

    public L getLeft() {
        return left;
    }

    public R getRight() {
        return right;
    }
}
