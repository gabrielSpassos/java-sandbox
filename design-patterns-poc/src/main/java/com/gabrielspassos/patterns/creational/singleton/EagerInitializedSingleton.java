package com.gabrielspassos.patterns.creational.singleton;

public class EagerInitializedSingleton {

    private static final EagerInitializedSingleton instance = new EagerInitializedSingleton();

    private EagerInitializedSingleton() {

    }

    public static EagerInitializedSingleton getInstance() {
        return instance;
    }
}
