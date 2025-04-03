package com.gabrielspassos.media;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class JsonMedia implements Media {

    private final ObjectMapper objectMapper;

    public JsonMedia() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public String print(Map<String, String> content) {
        try {
            return objectMapper.writeValueAsString(content);
        } catch (Exception e) {
            throw new RuntimeException("Error converting to JSON", e);
        }
    }

}
