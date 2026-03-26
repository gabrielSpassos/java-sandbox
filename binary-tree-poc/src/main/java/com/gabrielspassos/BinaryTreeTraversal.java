package com.gabrielspassos;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTreeTraversal {

    private BinaryTreeTraversal() {}

    // DFS - InOrder (Left, Root, Right)
    public static <T> void inOrder(Node<T> node) {
        if (node == null) return;

        inOrder(node.getLeft());
        IO.print(node.getValue() + " ");
        inOrder(node.getRight());
    }

    // DFS - PreOrder (Root, Left, Right)
    public static <T> void preOrder(Node<T> node) {
        if (node == null) return;

        IO.print(node.getValue() + " ");
        preOrder(node.getLeft());
        preOrder(node.getRight());
    }

    // DFS - PostOrder (Left, Right, Root)
    public static <T> void postOrder(Node<T> node) {
        if (node == null) return;

        postOrder(node.getLeft());
        postOrder(node.getRight());
        IO.print(node.getValue() + " ");
    }

    // BFS - Level Order
    public static <T> void levelOrder(Node<T> root) {
        if (root == null) return;

        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            var current = queue.poll();
            IO.print(current.getValue() + " ");

            if (current.getLeft() != null) queue.add(current.getLeft());
            if (current.getRight() != null) queue.add(current.getRight());
        }
    }
}
