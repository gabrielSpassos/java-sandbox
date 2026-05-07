package com.gabrielspassos;

import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

class StackTest {

    @Test
    void shouldCreateEmptyStack() {
        var stack = new Stack<Integer>();

        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }

    @Test
    void shouldPushAndPopStack() {
        var stack = new Stack<Integer>();

        boolean push = stack.push(1);
        assertTrue(push);

        assertEquals(1, stack.pop());
    }

    @Test
    void shouldThrowErrorToPopEmptyStack() {
        var stack = new Stack<Integer>();

        assertTrue(stack.isEmpty());
        assertThrows(EmptyStackException.class, stack::pop);
    }

    @Test
    void shouldDoubleStackSize() {
        var stack = new Stack<Integer>();

        for (int i = 1; i <= 10 ; i++) {
            assertTrue(stack.push(i));
        }

        assertEquals(10, stack.size());
        assertTrue(stack.push(11));
        assertEquals(11, stack.size());
    }

}