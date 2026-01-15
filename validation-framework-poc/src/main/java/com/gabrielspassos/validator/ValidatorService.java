package com.gabrielspassos.validator;

import com.gabrielspassos.structures.Pair;

import java.util.List;

public class ValidatorService<T> implements Validator<T> {

    @Override
    public Pair<List<String>, Boolean> validate(T object) {
        if (null == object) {
            throw new IllegalArgumentException("Object to be validated cannot be null");
        }

        IO.println("Validating object from class: " + object.getClass().getName());

        Class<?> clazz = object.getClass();

        return false;
    }
}
