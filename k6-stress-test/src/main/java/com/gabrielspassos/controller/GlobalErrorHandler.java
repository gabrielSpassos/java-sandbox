package com.gabrielspassos.controller;

import com.gabrielspassos.controller.response.ErrorResponse;
import com.gabrielspassos.exceptions.HttpException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalErrorHandler {

    @ResponseBody
    @ExceptionHandler(HttpException.class)
    public ResponseEntity<ErrorResponse> handleHttpException(HttpException httpException) {
        var error = new ErrorResponse(httpException.getMessage());

        return ResponseEntity.status(httpException.getStatusCode()).body(error);
    }
}
