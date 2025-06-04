package com.gabrielspassos.exception;

import org.springframework.http.HttpStatus;

public class TestErrorPurposeException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final String message;
    private final String code;

    public TestErrorPurposeException(HttpStatus httpStatus, String message, String code) {
        super(message);
        this.httpStatus = httpStatus;
        this.message = message;
        this.code = code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
