package com.gabrielspassos.queue;

import com.gabrielspassos.dll.DoubleLinkedList;
import com.gabrielspassos.dll.IDoubleLinkedList;

import java.util.Optional;

public class LinkedQueue<T> implements IQueue<T> {

    private final IDoubleLinkedList<T> doubleLinkedList = new DoubleLinkedList<>();
    private int size;

    @Override
    public boolean enqueue(T element) {
        var result = doubleLinkedList.insertAtEnd(element);
        size++;
        return result;
    }

    @Override
    public Optional<T> dequeue() {
        var result = Optional.ofNullable(doubleLinkedList.removeFromBeginning());
        if (0 < size) {
            size--;
        }
        return result;
    }

    @Override
    public int size() {
        return size;
    }
}
