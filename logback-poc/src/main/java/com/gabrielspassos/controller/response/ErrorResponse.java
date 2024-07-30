package com.gabrielspassos.controller.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ErrorResponse {

    private String message;
    private String code;
    private List<ErrorResponse> subErrors = new ArrayList<>();

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<ErrorResponse> getSubErrors() {
        return subErrors;
    }

    public void setSubErrors(List<ErrorResponse> subErrors) {
        this.subErrors = subErrors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorResponse that = (ErrorResponse) o;
        return Objects.equals(message, that.message)
                && Objects.equals(code, that.code)
                && Objects.equals(subErrors, that.subErrors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, code, subErrors);
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "message='" + message + '\'' +
                ", code='" + code + '\'' +
                ", subErrors=" + subErrors +
                '}';
    }
}
