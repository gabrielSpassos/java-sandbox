package com.gabrielspassos;

import java.util.HashMap;

public class MyCustomDocument {

    private final String content;

    public MyCustomDocument(String content) {
        this.content = content;
    }

    public String contentToMedia(Media media) {
        return media.print(new HashMap<>() {{ put("key", content); }});
    }
}
