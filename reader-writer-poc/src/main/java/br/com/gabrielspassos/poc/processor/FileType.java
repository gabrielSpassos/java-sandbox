package br.com.gabrielspassos.poc.processor;

import java.util.Arrays;

public enum FileType {
    FLAT_FILE("flat-file");

    private final String fileType;

    FileType(String fileType) {
        this.fileType = fileType;
    }

    public static Boolean contains(String type) {
        return Arrays.stream(FileType.values())
                .anyMatch(t -> t.fileType.equals(type));
    }

}
