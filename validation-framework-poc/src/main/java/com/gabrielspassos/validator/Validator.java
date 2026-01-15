package com.gabrielspassos.validator;

import com.gabrielspassos.structures.Pair;

import java.util.List;

public interface Validator<T> {

    Pair<List<String>, Boolean> validate(T object);

}
