package com.gabrielspassos.queue;

import java.util.Optional;

public interface IQueue<T> {

    boolean enqueue(T element);

    Optional<T> dequeue();

    int size();

}
