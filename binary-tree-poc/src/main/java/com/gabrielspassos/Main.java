package com.gabrielspassos;

public class Main {
    static void main() {
        IO.println("Binary Tree POC!");

        BinaryTree<Integer> tree = new BinaryTree<>();

        var root = tree.createNode(41);
        tree.setRoot(root);

        root.setLeft(tree.createNode(5));
        root.setRight(tree.createNode(36));

        root.getLeft().setLeft(tree.createNode(1));
        root.getLeft().setRight(tree.createNode(4));

        root.getRight().setLeft(tree.createNode(14));
        root.getRight().setRight(tree.createNode(22));

        root.getRight().getLeft().setLeft(tree.createNode(5));
        root.getRight().getLeft().setRight(tree.createNode(9));

        root.getRight().getRight().setLeft(tree.createNode(10));
        root.getRight().getRight().setRight(tree.createNode(12));

        Printer.print(tree.getRoot());

        IO.print("\nInOrder: ");
        BinaryTreeTraversal.inOrder(tree.getRoot());

        IO.print("\nPreOrder: ");
        BinaryTreeTraversal.preOrder(tree.getRoot());

        IO.print("\nPostOrder: ");
        BinaryTreeTraversal.postOrder(tree.getRoot());

        IO.print("\nLevelOrder: ");
        BinaryTreeTraversal.levelOrder(tree.getRoot());
    }
}
