package com.gabrielspassos.validator;

import com.gabrielspassos.annotations.NotNull;

public class NotNullValidator implements AnnotationValidator<NotNull, Object> {

    @Override
    public boolean isValid(NotNull annotation, Object value) {
        return null != value;
    }
}
