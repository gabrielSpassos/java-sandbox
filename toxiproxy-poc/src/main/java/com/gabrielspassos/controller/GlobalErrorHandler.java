package com.gabrielspassos.controller;

import com.gabrielspassos.controller.v1.response.ErrorResponse;
import com.gabrielspassos.exception.HttpException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(HttpException.class)
    public ResponseEntity<ErrorResponse> handleHttpException(HttpException httpException) {
        ErrorResponse errorResponse = new ErrorResponse(httpException.getMessage(), httpException.getCode());

        return ResponseEntity.status(httpException.getHttpStatus()).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException exception) {
        ErrorResponse errorResponse = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> {
                    String message = "Field: %s invalid. Message: %s"
                            .formatted(error.getField(), error.getDefaultMessage());
                    return new ErrorResponse(message, "INVALID_REQUEST");
                }).findFirst().get();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        IO.println("Unexpected error " + exception);
        ErrorResponse errorResponse = new ErrorResponse("Unexpected error", "UNEXPECTED_ERROR");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(errorResponse);
    }
}
