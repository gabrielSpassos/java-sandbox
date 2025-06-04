package com.gabrielspassos.media;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.util.Map;

public class XmlMedia implements Media {

    private final XmlMapper xmlMapper;

    public XmlMedia() {
        this.xmlMapper = new XmlMapper();
    }

    @Override
    public String print(Map<String, String> content) {
        try {
            return xmlMapper.writeValueAsString(content);
        } catch (Exception e) {
            throw new RuntimeException("Error converting to JSON", e);
        }
    }
}
