package com.gabrielspassos.poc.unsafe.exceptions;

public class BasicException extends RuntimeException {

    private String errorMessage;

    private String rawErrorMessage;

    private Object[] errorMessageArgs;

    public BasicException(String errorMessage, Object... errorMessageArgs) {
        super(String.format(errorMessage, errorMessageArgs));
        this.errorMessage = String.format(errorMessage, errorMessageArgs);
        this.rawErrorMessage = errorMessage;
        this.errorMessageArgs = errorMessageArgs;
    }

    public BasicException(String errorMessage, Throwable cause, Object... errorMessageArgs) {
        super(String.format(errorMessage, errorMessageArgs), cause);
        this.errorMessage = String.format(errorMessage, errorMessageArgs);
        this.rawErrorMessage = errorMessage;
        this.errorMessageArgs = errorMessageArgs;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getRawErrorMessage() {
        return rawErrorMessage;
    }

    public void setRawErrorMessage(String rawErrorMessage) {
        this.rawErrorMessage = rawErrorMessage;
    }

    public Object[] getErrorMessageArgs() {
        return errorMessageArgs;
    }

    public void setErrorMessageArgs(Object[] errorMessageArgs) {
        this.errorMessageArgs = errorMessageArgs;
    }
}
