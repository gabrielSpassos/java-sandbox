package com.gabrielspassos.exception;

public class HttpException extends RuntimeException {

    private final Integer httpStatus;

    public HttpException(Integer httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public Integer getHttpStatus() {
        return httpStatus;
    }
}
