package com.gabrielspassos.validator;

import com.gabrielspassos.structures.Pair;

import java.util.ArrayList;
import java.util.List;

public class ValidatorService<T> implements Validator<T> {

    @Override
    public Pair<List<String>, Boolean> validate(T object) {
        if (null == object) {
            return new Pair<>(List.of("Object to be validated is null"), false);
        }

        IO.println("Validating object from class: " + object.getClass().getName());

        List<String> errors = new ArrayList<>();
        Class<?> clazz = object.getClass();

        return new Pair<>(errors, false);
    }
}
