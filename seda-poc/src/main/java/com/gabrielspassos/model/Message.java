package com.gabrielspassos.model;

public class Message {

    private String id;
    private Long content;

    public Message(String id, Long content) {
        this.id = id;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public Long getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", content=" + content +
                '}';
    }
}
