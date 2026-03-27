package com.gabrielspassos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {

    @Test
    void shouldCreateBinarySearchTree() {
        int[] input = {10, 5, 15, 3, 7};

        Node<Integer> root = BinarySearchTree.fromArrayBST(input);

        assertNotNull(root);
        Printer.print(root);
    }

}