package com.gabrielspassos.dll;

public class DoubleLinkedList<T> implements IDoubleLinkedList<T> {

    private Node<T> head;
    private Node<T> tail;

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
    public T removeFromBeginning() {
        if (null == head) {
            return null;
        }

        Node<T> current = head;
        T data = head.getData();

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

        return data;
    }
}
