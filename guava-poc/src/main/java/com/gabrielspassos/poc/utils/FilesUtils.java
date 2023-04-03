package com.gabrielspassos.poc.utils;

import com.google.common.base.Charsets;
import com.google.common.io.CharSink;
import com.google.common.io.Files;
import com.google.common.io.Resources;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class FilesUtils {

    public String createAndReadFile(String content) throws IOException {
        File file = new File("test.txt");
        Files.asCharSink(file, Charsets.UTF_8).write(content);
        return Files.asCharSource(file, Charsets.UTF_8).read();
    }

    public List<String> createAndReadFileWithMultipleLines(List<String> content) throws IOException {
        File file = new File("test.txt");
        CharSink sink = Files.asCharSink(file, Charsets.UTF_8);
        sink.writeLines(content, "\n");

        return Files.readLines(file, Charsets.UTF_8);
    }

    public String readFileFromResources() throws IOException {
        URL url = Resources.getResource("person.json");
        return Resources.toString(url, Charsets.UTF_8);
    }

}
