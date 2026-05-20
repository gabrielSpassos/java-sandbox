package com.gabrielspassos.controller;

import com.gabrielspassos.controller.v1.response.ErrorResponse;
import com.gabrielspassos.exception.HttpException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(HttpException.class)
    public ResponseEntity<ErrorResponse> handleHttpException(HttpException httpException) {
        ErrorResponse errorResponse = new ErrorResponse(httpException.getMessage(), httpException.getCode());

        return ResponseEntity.status(httpException.getHttpStatus()).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse("Unexpected error", "UNEXPECTED_ERROR");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(errorResponse);
    }
}
