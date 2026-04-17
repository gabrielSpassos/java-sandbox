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
        List<Integer> expected = List.of(36, 22, 14, 13, 9, 12, 2, 5, 8, 7, 2, 10, 2, 2);

        SegmentTreeV2 segmentTreeV2 = new SegmentTreeV2(input);

        int[] array = segmentTreeV2.getArray();
        assertNotNull(array);
        assertEquals(input, array);
        
        int[] tree = segmentTreeV2.getTree();
        assertNotNull(tree);
        assertEquals(expected, Arrays.stream(tree).boxed().toList());
    }

}