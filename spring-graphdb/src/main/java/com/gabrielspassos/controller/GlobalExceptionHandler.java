package com.gabrielspassos.controller;

import com.gabrielspassos.dto.ErrorDto;
import com.gabrielspassos.exception.HttpException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpException.class)
    public ResponseEntity<ErrorDto> handle(HttpException ex) {
        return ResponseEntity
                .status(ex.getHttpStatus())
                .body(new ErrorDto(ex.getMessage(), ex.getCode()));
    }

}