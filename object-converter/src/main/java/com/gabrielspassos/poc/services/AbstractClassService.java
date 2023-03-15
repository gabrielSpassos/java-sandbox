package com.gabrielspassos.poc.services;

import com.gabrielspassos.poc.annotations.AttributeSynonym;
import com.gabrielspassos.poc.exceptions.ErrorToGetAttributeValueException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class AbstractClassService implements IClassService {

    protected abstract List<Field> getFieldsFromClass(Class<?> tClass);

    public Map<Field, Object> getAttributesAndValuesFromObject(Object object) {
        return getFieldsFromClass(object.getClass())
                .stream()
                .filter(field -> Objects.nonNull(getAttributeValue(field, object)))
                .collect(Collectors.toMap(Function.identity(), field -> getAttributeValue(field, object)));
    }

    public List<Field> getAccessibleFieldsFromObjectByOrigin(Object object, Field originField) throws NoSuchFieldException {
        List<String> fieldNames = getFieldWithSynonyms(originField);
        List<Field> fieldsFromClass = getFieldsFromClass(object.getClass());

        List<Field> fields = fieldsFromClass.stream()
                .filter(isFieldExistentOnValidNamesList(fieldNames))
                .peek(fieldFromObject -> fieldFromObject.setAccessible(true))
                .collect(Collectors.toList());

        if (fields.isEmpty()) {
            throw new NoSuchFieldException(originField.getName());
        }

        return fields;
    }

    public Field getFieldFromObjectByName(Object object, String fieldName) throws NoSuchFieldException {
        List<Field> fieldsFromClass = getFieldsFromClass(object.getClass());

        return fieldsFromClass.stream()
                .filter(field -> field.getName().equals(fieldName))
                .peek(fieldFromObject -> fieldFromObject.setAccessible(true))
                .findAny()
                .orElseThrow(() -> new NoSuchFieldException(fieldName));
    }

    private Object getAttributeValue(Field field, Object object) {
        try {
            field.setAccessible(true);
            return field.get(object);
        } catch (IllegalAccessException e) {
            throw new ErrorToGetAttributeValueException(e, field);
        }
    }

    private List<String> getFieldWithSynonyms(Field field) {
        if (!field.isAnnotationPresent(AttributeSynonym.class)) {
            return List.of(field.getName());
        }

        List<String> attributeSynonyms = getAttributeSynonyms(field);
        return new ArrayList<>() {{
            add(field.getName());
            addAll(attributeSynonyms);
        }};
    }

    private List<String> getAttributeSynonyms(Field field) {
        return Arrays.asList(field.getAnnotation(AttributeSynonym.class).synonyms());
    }

    private Predicate<Field> isFieldExistentOnValidNamesList(List<String> validFieldNames) {
        return fieldFromObject -> validFieldNames.stream()
                .anyMatch(validFieldName -> validFieldName.equals(fieldFromObject.getName()));
    }
}
