package com.gabrielspassos.patterns.creational.singleton;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class LazyInitializedSingletonTest {

    @Test
    void shouldHaveInstance() {
        var instance = LazyInitializedSingleton.getInstance();

        assertNotNull(instance);
    }

}