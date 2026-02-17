package com.gabrielspassos.patterns.creational.singleton;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EagerInitializedSingletonTest {

    @Test
    void shouldHaveInstance() {
        var instance = EagerInitializedSingleton.getInstance();

        assertNotNull(instance);
    }

}