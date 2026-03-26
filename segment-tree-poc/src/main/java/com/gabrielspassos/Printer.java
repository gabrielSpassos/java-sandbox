package com.gabrielspassos;

public class Printer {

    private Printer() {}

    public static void print(long size, long[] tree) {
        Node root = build(tree, size, 1);
        printNode(root, "", true);
    }

    private static Node build(long[] tree, long size, int index) {
        if (index >= tree.length) return null;

        if (index >= tree.length || tree[index] == 0 && index >= size) {
            return null;
        }

        Node node = new Node(tree[index]);

        int leftIndex = index * 2;
        int rightIndex = index * 2 + 1;

        if (leftIndex < tree.length) {
            node.left = build(tree, size, leftIndex);
        }

        if (rightIndex < tree.length) {
            node.right = build(tree, size, rightIndex);
        }

        return node;
    }

    private static void printNode(Node node, String prefix, boolean isTail) {
        if (node == null) return;

        System.out.println(prefix + (isTail ? "└── " : "├── ") + node.value);

        boolean hasLeft = node.left != null;
        boolean hasRight = node.right != null;

        if (hasLeft || hasRight) {
            printNode(node.left, prefix + (isTail ? "    " : "│   "), false);
            printNode(node.right, prefix + (isTail ? "    " : "│   "), true);
        }
    }

    private static class Node {
        long value;
        Node left;
        Node right;

        Node(long value) {
            this.value = value;
        }
    }
}
