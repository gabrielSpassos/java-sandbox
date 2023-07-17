package com.gabrielspassos.poc.unsafe.exceptions;

public class ErrorToPutAttributeValueException extends BasicException {

    public ErrorToPutAttributeValueException(Throwable cause, Object... errorMessageArgs) {
        super("Error to put value from field %s", cause, errorMessageArgs);
    }

}
