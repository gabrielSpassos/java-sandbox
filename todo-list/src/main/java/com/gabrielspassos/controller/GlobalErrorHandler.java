package com.gabrielspassos.controller;

import com.gabrielspassos.controller.response.ErrorResponse;
import com.gabrielspassos.exception.HttpException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(HttpException.class)
    public ResponseEntity<ErrorResponse> handleHttpException(HttpException httpException) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(httpException.getMessage());
        errorResponse.setCode(httpException.getCode());

        return ResponseEntity.status(httpException.getHttpStatus()).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("Unexpected error");
        errorResponse.setCode("UNEXPECTED_ERROR");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(errorResponse);
    }
}
