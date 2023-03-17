package com.gabrielspassos.poc.services;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CollectionsServiceTest {

    private static CollectionsService collectionsService;

    @BeforeAll
    public static void setup() {
        collectionsService = new CollectionsService();
    }

    @Test
    void shouldJoinStrings() {
        List<String> names = Lists.newArrayList("John", "Jane", "Adam", "Tom");

        String joined = collectionsService.joinStrings(names);

        assertEquals("John;Jane;Adam;Tom", joined);
    }

    @Test
    void shouldSplitStrings() {
        List<String> expected = Lists.newArrayList("apple", "banana", "orange");

        List<String> splits = collectionsService.split();

        assertEquals(expected, splits);
    }

}