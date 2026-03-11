package com.gabrielspassos;

import java.util.List;

public interface IDoubleLinkedList<T> {

    boolean insertAtBeginning(T data);

    boolean insertAtEnd(T data);

    boolean delete(T data);

    List<T> traverseForward();

    List<T> traverseBackward();

}
