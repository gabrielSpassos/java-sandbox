package com.gabrielspassos;

import java.util.Arrays;
import java.util.EmptyStackException;

public class Stack<T> {

    private Object[] elements;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public Stack() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public boolean push(T value) {
        ensureCapacity();
        elements[size++] = value;
        return true;
    }

    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        T value = (T) elements[--size];
        elements[size] = null; // avoid memory leak
        return value;
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            elements = Arrays.copyOf(elements, elements.length * 2);
        }
    }

    public boolean isEmpty() {
        return 0 == size;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(elements, size));
    }
}
