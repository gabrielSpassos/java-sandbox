package com.gabrielspassos.api.v1.requests;

import java.util.Objects;

public class MessageWithSessionRequest {

    private String from;
    private String message;
    private String sessionId;

    public MessageWithSessionRequest() {
    }

    public MessageWithSessionRequest(String from, String message, String sessionId) {
        this.from = from;
        this.message = message;
        this.sessionId = sessionId;
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

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MessageWithSessionRequest that = (MessageWithSessionRequest) o;
        return Objects.equals(from, that.from)
                && Objects.equals(message, that.message)
                && Objects.equals(sessionId, that.sessionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, message, sessionId);
    }

    @Override
    public String toString() {
        return "MessageWithSessionRequest{" +
                "from='" + from + '\'' +
                ", message='" + message + '\'' +
                ", sessionId='" + sessionId + '\'' +
                '}';
    }
}
