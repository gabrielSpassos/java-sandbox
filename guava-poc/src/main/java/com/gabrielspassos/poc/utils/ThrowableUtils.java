package com.gabrielspassos.poc.utils;

import com.google.common.base.Throwables;

public class ThrowableUtils {

    public void throwSomeError(Throwable throwable) {
        System.out.println("This will throw a Throwable" + throwable);
        Throwables.propagateIfPossible(throwable, RuntimeException.class);
        throw new IllegalArgumentException(throwable);
    }
}
