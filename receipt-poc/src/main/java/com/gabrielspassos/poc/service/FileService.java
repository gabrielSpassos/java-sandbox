package com.gabrielspassos.poc.service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Objects;

public class FileService {

    public static String readFile(String filename) throws IOException {
        URL resource = getResource(filename);

        File file = new File(resource.getFile());

        if (file.exists()) {
            return new String(Files.readAllBytes(file.toPath()));
        }

        throw new IllegalArgumentException("File not found");
    }

    private static URL getResource(String filename) {
        ClassLoader classLoader = FileService.class.getClassLoader();
        URL resource = classLoader.getResource(filename);

        if (Objects.isNull(resource)) throw new IllegalArgumentException("File not found");

        return resource;
    }
}
