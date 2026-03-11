package com.gabrielspassos;

import java.util.ArrayList;
import java.util.List;

public class DoubleLinkedList<T> implements IDoubleLinkedList<T> {

    private Node<T> head;
    private Node<T> tail;

    @Override
    public boolean insertAtBeginning(T data) {
        Node<T> newNode = new Node<T>(data);
        if (null == head) {
            head = tail = newNode;
            return true;
        }
        newNode.setNext(head);
        head.setPrev(newNode);
        head = newNode;
        return true;
    }

    @Override
    public boolean insertAtEnd(T data) {
        Node<T> newNode = new Node<T>(data);
        if (null == tail) {
            head = tail = newNode;
            return true;
        }
        tail.setNext(newNode);
        newNode.setPrev(tail);
        tail = newNode;
        return true;
    }

    @Override
    public boolean delete(T data) {
        Node<T> current = head;

        while (null != current) {
            if (current.getData().equals(data)) {

                if (null != current.getPrev()) {
                    current.getPrev().setNext(current.getNext());
                } else {
                    head = current.getNext();
                }

                if (null != current.getNext()) {
                    current.getNext().setPrev(current.getPrev());
                } else {
                    tail = current.getPrev();
                }

                return true;
            }

            current = current.getNext();
        }

        return false;
    }

    @Override
    public List<T> traverseForward() {
        List<T> result = new ArrayList<>();
        Node<T> current = head;

        while (null != current) {
            result.add(current.getData());
            current = current.getNext();
        }

        return result;
    }

    @Override
    public List<T> traverseBackward() {
        List<T> result = new ArrayList<>();
        Node<T> current = tail;

        while (null != current) {
            result.add(current.getData());
            current = current.getPrev();
        }

        return result;
    }
}
