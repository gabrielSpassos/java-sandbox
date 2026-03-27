package com.gabrielspassos;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SegmentTreeTest {

    @Test
    void shouldCreateTree() {
        long[] input = {10, 5, 15, 3, 7};
        List<Long> expected = List.of(0L, 40L, 20L, 20L, 10L, 10L, 5L, 15L, 3L, 7L);

        SegmentTree segmentTree = new SegmentTree(input);

        assertNotNull(segmentTree);
        assertNotNull(segmentTree.getTree());

        List<Long> tree = Arrays.stream(segmentTree.getTree()).boxed().toList();
        assertEquals(expected, tree);
    }

}