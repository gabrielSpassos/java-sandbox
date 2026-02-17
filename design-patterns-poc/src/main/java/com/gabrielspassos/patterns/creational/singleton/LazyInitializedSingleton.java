package com.gabrielspassos.patterns.creational.singleton;

public class LazyInitializedSingleton {

    private static LazyInitializedSingleton instance;

    private LazyInitializedSingleton() {

    }

    public static LazyInitializedSingleton getInstance() {
        if (null == instance) {
            instance = new LazyInitializedSingleton();
        }
        return instance;
    }
}
