package com.gabrielspassos.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends HttpException {

    public BadRequestException(String message, String code) {
        super(message, HttpStatus.BAD_REQUEST.value(), code);
    }

}
