package com.gabrielspassos.validator;

public class ValidatorService<T> implements Validator<T> {

    @Override
    public boolean validate(T object) {
        IO.println("Validating object from class: " + object.getClass().getSimpleName());
        return false;
    }

}
