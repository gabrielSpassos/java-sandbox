package com.gabrielspassos.validator;

import java.lang.annotation.Annotation;

public interface AnnotationValidator<A extends Annotation, T> {

    boolean isValid(A annotation, T value);

}
