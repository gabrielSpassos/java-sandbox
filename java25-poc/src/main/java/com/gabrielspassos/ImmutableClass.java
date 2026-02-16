package com.gabrielspassos;

public class ImmutableClass {

    private final StableValue<Boolean> isAlreadyExecuted = StableValue.of();

    public void execute() {
        IO.println("Before execute: " + isAlreadyExecuted);
        isAlreadyExecuted.orElseSet(() -> true);
        IO.println("After execute: " + isAlreadyExecuted);
    }
}
