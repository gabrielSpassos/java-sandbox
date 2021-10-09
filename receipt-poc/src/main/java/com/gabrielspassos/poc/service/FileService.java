package com.gabrielspassos.poc.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileService {

    public static String readFile(String filename) throws IOException {
        ClassLoader classLoader = FileService.class.getClassLoader();

        File file = new File(classLoader.getResource(filename).getFile());

        if (file.exists()) {
            return new String(Files.readAllBytes(file.toPath()));
        }

        throw new IllegalArgumentException("File not found");
    }
}
