package com.gabrielspassos.poc.exceptions;

public class ErrorToInstantiateClassException extends BasicException {
    public ErrorToInstantiateClassException(Throwable cause, Object... errorMessageArgs) {
        super("Error to create instance of class %s", cause, errorMessageArgs);
    }
}
