package com.gabrielspassos.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends HttpException {

    public NotFoundException(String message, String code) {
        super(message, HttpStatus.NOT_FOUND.value(), code);
    }

}
