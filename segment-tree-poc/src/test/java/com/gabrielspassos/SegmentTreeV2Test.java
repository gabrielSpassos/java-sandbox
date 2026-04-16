package com.gabrielspassos;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SegmentTreeV2Test {

    @Test
    void shouldCreateSegmentTree() {
        int[] input = {1, 4, 5, 9, 10, 12};
        List<Integer> expected = List.of(41, 10, 31, 5, 5, 19, 12, 1, 4, 5, 9, 10, 12);

        SegmentTreeV2 segmentTreeV2 = new SegmentTreeV2(input);

        segmentTreeV2.build(1, 0, input.length - 1);

        int[] tree = segmentTreeV2.getTree();
        assertNotNull(tree);
        assertEquals(expected, Arrays.stream(tree).boxed().toList());
    }

}