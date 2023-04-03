package com.gabrielspassos.poc.utils;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class FilesUtilsTest {

    private static FilesUtils filesUtils;

    @BeforeAll
    public static void setup() {
        filesUtils = new FilesUtils();
    }

    @Test
    void shouldCreateAndReadFile() throws IOException {
        String input = "Hello world";

        String content = filesUtils.createAndReadFile(input);

        assertEquals(input, content);
    }

    @Test
    void shouldCreateAndReadFileWithMultipleLine() throws IOException {
        List<String> names = Lists.newArrayList("John", "Jane", "Adam", "Tom");

        List<String> content = filesUtils.createAndReadFileWithMultipleLines(names);

        assertFalse(content.isEmpty());
        assertEquals(4, content.size());
        assertEquals("John", content.get(0));
        assertEquals("Jane", content.get(1));
        assertEquals("Adam", content.get(2));
        assertEquals("Tom", content.get(3));
    }

    @Test
    void shouldReadFileFromResources() throws IOException {
        String content = filesUtils.readFileFromResources();

        assertEquals(contentFromResources(), content);
    }

    private String contentFromResources() {
        return """
                {
                  "name": "John",
                  "age": 52,
                  "height": 1.83,
                  "isMarried": false,
                  "children": [{
                    "name": "Marry",
                    "age": 20,
                    "height": 1.71,
                    "isMarried": true,
                    "children": []
                  }]
                }""";
    }

}