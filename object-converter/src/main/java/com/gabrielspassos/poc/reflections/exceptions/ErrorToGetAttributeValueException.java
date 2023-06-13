package com.gabrielspassos.poc.reflections.exceptions;

public class ErrorToGetAttributeValueException extends BasicException {

    public ErrorToGetAttributeValueException(Throwable cause, Object... errorMessageArgs) {
        super("Error to get value from field %s", cause, errorMessageArgs);
    }
}
