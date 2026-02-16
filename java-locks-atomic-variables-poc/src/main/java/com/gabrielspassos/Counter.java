package com.gabrielspassos;

public abstract class Counter {

    private int value = 0;

    public abstract void increment();

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
