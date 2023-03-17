package com.gabrielspassos.poc.services;

import com.google.common.base.Throwables;

public class ThrowableService {

    public void throwSomeError(Throwable throwable) {
        try {
            System.out.println("This will throw a Throwable" + throwable);
            throw throwable;
        } catch (Throwable t) {
            Throwables.propagateIfPossible(t, RuntimeException.class);
            throw new IllegalArgumentException(t);
        }
    }
}
