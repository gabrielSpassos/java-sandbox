package com.gabrielspassos.exception;

import org.springframework.http.HttpStatus;

public abstract class BaseException extends RuntimeException{

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    public BaseException(HttpStatus httpStatus, String code, String message) {
        super(message);
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
