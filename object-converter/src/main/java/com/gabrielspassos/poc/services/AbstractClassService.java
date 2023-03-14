package com.gabrielspassos.poc.services;

import com.gabrielspassos.poc.exceptions.ErrorToGetAttributeValueException;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class AbstractClassService implements IClassService {

    protected abstract List<Field> getFieldsFromClass(Class<?> tClass);

    public Map<String, Object> getAttributesNamesAndValuesFromObject(Object object) {
        return getFieldsFromClass(object.getClass())
                .stream()
                .collect(Collectors.toMap(Field::getName, field -> getAttributeValue(field, object)));
    }

    public Field getAccessibleFieldByName(Object object, String fieldName) throws NoSuchFieldException {
        List<Field> fieldsFromClass = getFieldsFromClass(object.getClass());
        return fieldsFromClass.stream()
                .filter(field -> field.getName().equals(fieldName))
                .peek(field -> field.setAccessible(true))
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
}
