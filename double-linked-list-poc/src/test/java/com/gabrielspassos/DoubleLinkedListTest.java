package com.gabrielspassos;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DoubleLinkedListTest {

    @Test
    void shouldAddAtBeginning() {
        DoubleLinkedList<Integer> doubleLinkedList = new DoubleLinkedList<>();

        assertTrue(doubleLinkedList.insertAtBeginning(1));
        assertTrue(doubleLinkedList.insertAtBeginning(2));
        assertTrue(doubleLinkedList.insertAtBeginning(3));
        assertEquals(List.of(3, 2, 1), doubleLinkedList.traverseForward());
    }

    @Test
    void shouldAddAtEnd() {
        DoubleLinkedList<Integer> doubleLinkedList = new DoubleLinkedList<>();

        assertTrue(doubleLinkedList.insertAtEnd(1));
        assertTrue(doubleLinkedList.insertAtEnd(2));
        assertTrue(doubleLinkedList.insertAtEnd(3));
        assertEquals(List.of(1, 2, 3), doubleLinkedList.traverseForward());
    }

    @Test
    void shouldDelete() {
        DoubleLinkedList<Integer> doubleLinkedList = new DoubleLinkedList<>();

        doubleLinkedList.insertAtEnd(1);
        doubleLinkedList.insertAtEnd(2);
        doubleLinkedList.insertAtEnd(3);

        assertTrue(doubleLinkedList.delete(2));

        assertEquals(List.of(1, 3), doubleLinkedList.traverseForward());
    }

    @Test
    void shouldNotDelete() {
        DoubleLinkedList<Integer> doubleLinkedList = new DoubleLinkedList<>();

        doubleLinkedList.insertAtEnd(1);
        doubleLinkedList.insertAtEnd(2);
        doubleLinkedList.insertAtEnd(3);

        assertFalse(doubleLinkedList.delete(4));

        assertEquals(List.of(1, 2, 3), doubleLinkedList.traverseForward());
    }

    @Test
    void shouldTransverseForward() {
        DoubleLinkedList<Integer> doubleLinkedList = new DoubleLinkedList<>();

        doubleLinkedList.insertAtEnd(1);
        doubleLinkedList.insertAtEnd(2);
        doubleLinkedList.insertAtEnd(3);

        assertEquals(List.of(1, 2, 3), doubleLinkedList.traverseForward());
    }

    @Test
    void shouldTransverseBackward() {
        DoubleLinkedList<Integer> doubleLinkedList = new DoubleLinkedList<>();

        doubleLinkedList.insertAtEnd(1);
        doubleLinkedList.insertAtEnd(2);
        doubleLinkedList.insertAtEnd(3);

        assertEquals(List.of(3, 2, 1), doubleLinkedList.traverseBackward());
    }

}