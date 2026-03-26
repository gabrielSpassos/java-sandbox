package com.gabrielspassos;

import java.util.Arrays;

public class Main {
    static void main() {
        IO.println("Segment Tree POC!");

        long[] data = {1, 4, 5, 9, 10, 12};

        SegmentTree segmentTree = new SegmentTree(data);

        IO.println("Input: " + Arrays.toString(data));
        IO.println(segmentTree);

        Printer.print(segmentTree.getSize(), segmentTree.getTree());

        long query1 = segmentTree.query(1, 5);
        IO.println("Query [1, 5]: " + query1);

        IO.println("Update index: 2 value: 10");
        segmentTree.update(2, 10);
        IO.println(segmentTree);

        Printer.print(segmentTree.getSize(), segmentTree.getTree());

        long query2 = segmentTree.query(1, 5);
        IO.println("After update Query [1, 5]: " + query2);
    }
}
