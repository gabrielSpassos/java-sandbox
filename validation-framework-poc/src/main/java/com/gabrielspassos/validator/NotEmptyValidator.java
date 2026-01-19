package com.gabrielspassos.validator;

import com.gabrielspassos.annotations.NotEmpty;

import java.lang.reflect.Array;

public class NotEmptyValidator implements AnnotationValidator<NotEmpty, Object> {

    @Override
    public boolean isValid(NotEmpty annotation, Object value) {
        switch (value) {
            case null -> {
                return false;
            }
            case String s -> {
                return !s.isEmpty();
            }
            case Iterable iterable -> {
                return iterable.iterator().hasNext();
            }
            default -> {
            }
        }

        if (value.getClass().isArray()) {
            return Array.getLength(value) > 0;
        }

        return true;
    }

}
