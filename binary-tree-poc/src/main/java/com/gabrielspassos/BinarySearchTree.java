package com.gabrielspassos;

public class BinarySearchTree {

    public static Node<Integer> fromArrayBST(int[] array) {
        Node<Integer> root = null;

        for (int value : array) {
            root = insert(root, value);
        }

        return root;
    }

    public static Node<Integer> insert(Node<Integer> root, int value) {
        if (root == null) return new Node<>(value);

        if (value < root.getValue()) {
            root.setLeft(insert(root.getLeft(), value));
        } else {
            root.setRight(insert(root.getRight(), value));
        }

        return root;
    }
}
