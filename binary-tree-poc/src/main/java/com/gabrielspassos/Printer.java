package com.gabrielspassos;

public class Printer {

    private Printer() {}

    public static <T> void print(Node<T> root) {
        printNode(root, "", true);
    }

    private static <T> void printNode(Node<T> node, String prefix, boolean isTail) {
        if (node == null) return;

        System.out.println(prefix + (isTail ? "└── " : "├── ") + node.getValue());

        boolean hasLeft = node.getLeft() != null;
        boolean hasRight = node.getRight() != null;

        if (hasLeft || hasRight) {
            printNode(node.getLeft(), prefix + (isTail ? "    " : "│   "), false);
            printNode(node.getRight(), prefix + (isTail ? "    " : "│   "), true);
        }
    }
}
