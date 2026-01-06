package com.gabrielspassos.api.v1.requests;

import java.util.Objects;

public class MessageRequest {

    private String from;
    private String message;

    public MessageRequest() {
    }

    public MessageRequest(String from, String message) {
        this.from = from;
        this.message = message;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MessageRequest messageRequest1 = (MessageRequest) o;
        return Objects.equals(from, messageRequest1.from) && Objects.equals(message, messageRequest1.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, message);
    }

    @Override
    public String toString() {
        return "Message{" +
                "from='" + from + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
