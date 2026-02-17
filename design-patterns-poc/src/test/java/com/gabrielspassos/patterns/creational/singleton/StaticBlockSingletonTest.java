package com.gabrielspassos.patterns.creational.singleton;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StaticBlockSingletonTest {

    @Test
    void shouldHaveInstance() {
        var instance = StaticBlockSingleton.getInstance();

        assertNotNull(instance);
    }

}