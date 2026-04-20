package com.gabrielspassos;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SegmentTreeV2Test {

    @Test
    void shouldCreateSegmentTree() {
        int[] input = {5, 8, 7, 2, 10, 2, 2};
        List<Integer> expected = List.of(36, 22, 14, 13, 9, 12, 2, 5, 8, 7, 2, 10, 2);

        SegmentTreeV2 segmentTreeV2 = new SegmentTreeV2(input);

        int[] array = segmentTreeV2.getArray();
        assertNotNull(array);
        assertEquals(input, array);

        int[] tree = segmentTreeV2.getTree();
        assertNotNull(tree);
        List<Integer> actual = Arrays.stream(tree)
                .limit(13)
                .boxed()
                .toList();
        assertEquals(expected, actual);
    }

    @Test
    void shouldQuerySegmentTree() {
        int[] input = {5, 8, 7, 2, 10, 2, 2};

        SegmentTreeV2 segmentTreeV2 = new SegmentTreeV2(input);

        assertEquals(36, segmentTreeV2.query(0, 6));
        assertEquals(22, segmentTreeV2.query(0, 3));
        assertEquals(14, segmentTreeV2.query(4, 6));
    }

    @Test
    void shouldUpdateSegmentTree() {
        int[] input = {5, 8, 7, 2, 10, 2, 2};
        int[] updatedArray = {5, 8, 7, 6, 10, 2, 2};
        List<Integer> expected = List.of(40, 26, 14, 13, 13, 12, 2, 5, 8, 7, 6, 10, 2);

        SegmentTreeV2 segmentTreeV2 = new SegmentTreeV2(input);
        segmentTreeV2.update(3, 6);

        int[] array = segmentTreeV2.getArray();
        assertNotNull(array);
        assertEquals(toList(updatedArray), toList(array));

        int[] tree = segmentTreeV2.getTree();
        assertNotNull(tree);
        List<Integer> actual = Arrays.stream(tree)
                .limit(13)
                .boxed()
                .toList();
        assertEquals(expected, actual);
    }

    private List<Integer> toList(int[] array) {
        return Arrays.stream(array).boxed().toList();
    }
}