package com.gabrielspassos;

public class BinaryTree<T> {

    private Node<T> root;

    public Node<T> getRoot() {
        return root;
    }

    public void setRoot(Node<T> root) {
        this.root = root;
    }

    public Node<T> createNode(T value) {
        return new Node<>(value);
    }

}
