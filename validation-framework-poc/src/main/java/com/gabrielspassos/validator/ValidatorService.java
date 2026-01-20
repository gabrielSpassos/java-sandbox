package com.gabrielspassos.validator;

import com.gabrielspassos.annotations.Constraint;
import com.gabrielspassos.structures.Pair;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
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

                        AnnotationValidator annotationValidator = constraint.validatedBy().getDeclaredConstructor().newInstance();

                        Object value = field.get(object);

                        if (!annotationValidator.isValid(annotation, value)) {
                            var validationErrorMessage = extractMessage(annotation, field);
                            errors.add(validationErrorMessage);
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

    private String extractMessage(Annotation annotation, Field field) {
        try {
            Method messageMethod = annotation.annotationType().getMethod("message");
            return (String) messageMethod.invoke(annotation);
        } catch (NoSuchMethodException e) {
            return "Field " + field.getName() + " is invalid";
        } catch (Exception e) {
            throw new RuntimeException("Failed to read annotation message", e);
        }
    }
}
