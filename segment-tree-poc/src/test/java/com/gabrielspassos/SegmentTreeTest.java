package com.gabrielspassos;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SegmentTreeTest {

    @Test
    void shouldBuildTree() {
        long[] input = {1, 4, 5, 9, 10, 12};
        List<Long> expected = List.of(0L, 41L, 36L, 5L, 14L, 22L, 1L, 4L, 5L, 9L, 10L, 12L);

        SegmentTree segmentTree = new SegmentTree(input);

        assertNotNull(segmentTree);
        assertNotNull(segmentTree.getTree());

        List<Long> tree = Arrays.stream(segmentTree.getTree()).boxed().toList();
        assertEquals(expected, tree);
    }

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

    @Test
    void shouldUpdateTree() {
        long[] input = {10, 5, 15, 3, 7};
        List<Long> expected = List.of(0L, 61L, 41L, 20L, 31L, 10L, 5L, 15L, 24L, 7L);

        SegmentTree segmentTree = new SegmentTree(input);

        assertNotNull(segmentTree);
        assertNotNull(segmentTree.getTree());

        segmentTree.update(3, 24);

        List<Long> tree = Arrays.stream(segmentTree.getTree()).boxed().toList();
        assertEquals(expected, tree);
    }

    @Test
    void shouldQuery() {
        long[] input = {10, 5, 15, 3, 7};

        SegmentTree segmentTree = new SegmentTree(input);

        assertNotNull(segmentTree);
        assertNotNull(segmentTree.getTree());

        long query = segmentTree.query(2, 4);
        assertEquals(18, query);
    }

}