package com.gabrielspassos.api.v1.responses;

import java.util.Objects;

public class MessageResponse {

    private String from;
    private String message;
    private String dateTime;

    public MessageResponse() {
    }

    public MessageResponse(String from, String message, String dateTime) {
        this.from = from;
        this.message = message;
        this.dateTime = dateTime;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
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
        MessageResponse that = (MessageResponse) o;
        return Objects.equals(from, that.from)
                && Objects.equals(message, that.message)
                && Objects.equals(dateTime, that.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, message, dateTime);
    }

    @Override
    public String toString() {
        return "MessageResponse{" +
                "from='" + from + '\'' +
                ", message='" + message + '\'' +
                ", dateTime='" + dateTime + '\'' +
                '}';
    }
}
