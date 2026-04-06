package com.gabrielspassos.dll;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoubleLinkedListTest {

    @Test
    void shouldAddAtEnd() {
        IDoubleLinkedList<Integer> doubleLinkedList = new DoubleLinkedList<>();

        assertTrue(doubleLinkedList.insertAtEnd(1));
        assertTrue(doubleLinkedList.insertAtEnd(2));
        assertTrue(doubleLinkedList.insertAtEnd(3));
    }

    @Test
    void shouldRemoveFromBeginning() {
        IDoubleLinkedList<Integer> doubleLinkedList = new DoubleLinkedList<>();

        doubleLinkedList.insertAtEnd(4);
        doubleLinkedList.insertAtEnd(5);
        doubleLinkedList.insertAtEnd(6);

        assertEquals(4, doubleLinkedList.removeFromBeginning());
        assertEquals(5, doubleLinkedList.removeFromBeginning());
        assertEquals(6, doubleLinkedList.removeFromBeginning());
    }

    @Test
    void shouldReturnNullWithEmptyListToRemoveFromBeginning() {
        IDoubleLinkedList<Integer> doubleLinkedList = new DoubleLinkedList<>();

        assertNull(doubleLinkedList.removeFromBeginning());
    }

}