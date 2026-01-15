package com.gabrielspassos.validator;

public interface Validator<T> {

    boolean validate(T object);
}
