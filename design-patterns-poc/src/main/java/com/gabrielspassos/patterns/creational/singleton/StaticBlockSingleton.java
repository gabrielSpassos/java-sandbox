package com.gabrielspassos.patterns.creational.singleton;

public class StaticBlockSingleton {

    private static StaticBlockSingleton instance;

    private StaticBlockSingleton() {

    }

    static {
        try {
            instance = new StaticBlockSingleton();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static StaticBlockSingleton getInstance() {
        return instance;
    }
}
