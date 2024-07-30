package com.gabrielspassos.controller;

import com.gabrielspassos.controller.response.ErrorResponse;
import com.gabrielspassos.exception.TestErrorPurposeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalErrorHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(TestErrorPurposeException.class)
    public ResponseEntity<ErrorResponse> errorResponse(TestErrorPurposeException exception)  {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setCode(exception.getCode());

        logger.error("Error status: {}, body: {}", exception.getHttpStatus(), errorResponse);
        return ResponseEntity.status(exception.getHttpStatus()).body(errorResponse);
    }

}
