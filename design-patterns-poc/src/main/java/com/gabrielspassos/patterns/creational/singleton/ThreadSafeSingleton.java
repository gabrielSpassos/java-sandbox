package com.gabrielspassos.patterns.creational.singleton;

public class ThreadSafeSingleton {

    private static ThreadSafeSingleton instance;

    private ThreadSafeSingleton() {

    }

    public static synchronized ThreadSafeSingleton getInstance() {
        if (null == instance) {
            instance = new ThreadSafeSingleton();
        }
        return instance;
    }

    public static ThreadSafeSingleton getInstanceUsingDoubleLocking() {
        if (null == instance) {
            synchronized (ThreadSafeSingleton.class) {
                if (null == instance) {
                    instance = new ThreadSafeSingleton();
                }
            }
        }
        return instance;
    }
}
