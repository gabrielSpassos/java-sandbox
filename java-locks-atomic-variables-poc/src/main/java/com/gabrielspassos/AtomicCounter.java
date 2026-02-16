package com.gabrielspassos;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter extends Counter {

    private final AtomicInteger value = new AtomicInteger(0);

    @Override
    public void increment() {
        value.incrementAndGet();
    }

    @Override
    public int getValue() {
        return value.get();
    }

}
