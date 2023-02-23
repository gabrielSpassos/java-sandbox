package com.gabrielspassos.poc.exceptions;

public class InvalidClassConstructorException extends BasicException {

    public InvalidClassConstructorException(Object... errorMessageArgs) {
        super("%s doesn't contains a valid default constructor", errorMessageArgs);
    }
}
