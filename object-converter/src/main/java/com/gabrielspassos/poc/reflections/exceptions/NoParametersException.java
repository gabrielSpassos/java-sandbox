package com.gabrielspassos.poc.reflections.exceptions;

public class NoParametersException extends BasicException {

    public NoParametersException(Object... errorMessageArgs) {
        super("Can not use null value", errorMessageArgs);
    }

}
