package com.gabrielspassos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Challenge01Test {

    @Test
    void revertString() {
        var reverted = Challenge01.revert("Hello");

        assertEquals("olleH", reverted);
    }

}