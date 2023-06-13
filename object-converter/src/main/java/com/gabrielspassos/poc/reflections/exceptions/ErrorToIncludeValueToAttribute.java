package com.gabrielspassos.poc.reflections.exceptions;

public class ErrorToIncludeValueToAttribute extends BasicException {

    public ErrorToIncludeValueToAttribute(Throwable cause, Object... errorMessageArgs) {
        super("Error to include value %s to attribute %s", cause, errorMessageArgs);
    }
}
