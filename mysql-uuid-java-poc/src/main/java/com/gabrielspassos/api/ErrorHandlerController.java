package com.gabrielspassos.api;

import com.gabrielspassos.dto.ErrorDTO;
import com.gabrielspassos.exception.BaseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ErrorHandlerController {

    @ResponseBody
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorDTO> handleBaseException(BaseException baseException) {
        var errorDTO = new ErrorDTO(baseException.getCode(), baseException.getMessage());

        return ResponseEntity.status(baseException.getHttpStatus()).body(errorDTO);
    }
}
