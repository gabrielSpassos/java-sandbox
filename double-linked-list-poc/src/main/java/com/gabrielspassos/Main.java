package com.gabrielspassos;

public class Main {
    static void main() {
        IO.println("Double Linked List POC!");

        DoubleLinkedList<Integer> doubleLinkedList = new DoubleLinkedList<>();

        doubleLinkedList.insertAtBeginning(1);
        doubleLinkedList.insertAtBeginning(2);
        doubleLinkedList.insertAtBeginning(3);

        IO.println("Forward: " + doubleLinkedList.traverseForward());
        IO.println("Backward: " + doubleLinkedList.traverseBackward());

        doubleLinkedList.delete(3);
        doubleLinkedList.delete(4);
        doubleLinkedList.delete(1);
        doubleLinkedList.delete(2);

        IO.println("Empty Forward: " + doubleLinkedList.traverseForward());
        IO.println("Empty Backward: " + doubleLinkedList.traverseBackward());

        doubleLinkedList.insertAtEnd(1);
        doubleLinkedList.insertAtEnd(2);
        doubleLinkedList.insertAtEnd(3);

        IO.println("Forward: " + doubleLinkedList.traverseForward());
        IO.println("Backward: " + doubleLinkedList.traverseBackward());
    }
}
