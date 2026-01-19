package com.gabrielspassos.validator;

import com.gabrielspassos.annotations.Constraint;
import com.gabrielspassos.structures.Pair;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ValidatorService<T> implements Validator<T> {

    @Override
    public Pair<List<String>, Boolean> validate(T object) {
        try {
            if (null == object) {
                return new Pair<>(List.of("Object to be validated is null"), false);
            }

            IO.println("Validating object from class: " + object.getClass().getName());

            List<String> errors = new ArrayList<>();
            Class<?> clazz = object.getClass();

            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);

                for (Annotation annotation: field.getAnnotations()) {
                    if (annotation.annotationType().isAnnotationPresent(Constraint.class)) {

                        Constraint constraint = annotation.annotationType().getAnnotation(Constraint.class);

                        AnnotationValidator validator = constraint.validatedBy().getDeclaredConstructor().newInstance();

                        Object value = field.get(object);

                        if (!validator.isValid(annotation, value)) {
                            errors.add("Field '" + field.getName() + "' is invalid.");
                        }
                    }
                }
            }

            var isValid = errors.isEmpty();
            return new Pair<>(errors, isValid);
        } catch (Exception e) {
            return new Pair<>(List.of("Validation failed for class " + object.getClass().getName()), false);
        }
    }
}
