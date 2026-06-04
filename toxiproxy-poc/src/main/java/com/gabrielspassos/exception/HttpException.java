package com.gabrielspassos.exception;

public class HttpException extends RuntimeException{

    private final Integer httpStatus;
    private final String message;
    private final String code;

    public HttpException(String message, Integer httpStatus, String code) {
        super(message);
        this.httpStatus = httpStatus;
        this.message = message;
        this.code = code;
    }

    public Integer getHttpStatus() {
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
