package com.gabrielspassos;

public class Main {
    static void main() {
        IO.println("Segment Tree POC!");

        long[] data = {2, 4, 5, 7, 8, 9};

        SegmentTree segmentTree = new SegmentTree(data);

        IO.println(segmentTree);

        long query1 = segmentTree.query(1, 5);
        IO.println("Query [1, 5]: " + query1);

        segmentTree.update(2, 10);
        IO.println(segmentTree);

        long query2 = segmentTree.query(1, 5);
        IO.println("After update Query [1, 5]: " + query2);
    }
}
