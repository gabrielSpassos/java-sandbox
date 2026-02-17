package com.gabrielspassos.patterns.creational.singleton;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ThreadSafeSingletonTest {

    @Test
    void shouldHaveInstance() {
        var instance = ThreadSafeSingleton.getInstance();

        assertNotNull(instance);
    }

    @Test
    void shouldHaveInstanceWithDoubleCheck() {
        var instance = ThreadSafeSingleton.getInstanceUsingDoubleLocking();

        assertNotNull(instance);
    }

}