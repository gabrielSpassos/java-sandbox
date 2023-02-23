package com.gabrielspassos.poc.services;

import com.gabrielspassos.poc.exceptions.ErrorToGetAttributeValueException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClassService {

    private static ClassService instance;

    private ClassService() {
    }

    public static synchronized ClassService getClassService(){
        if (instance == null) {
            instance = new ClassService();
        }
        return instance;
    }

    public Map<String, Object> getAttributesNamesAndValuesFromObject(Object object) {
        return getFieldsFromClass(object.getClass())
                .stream()
                .collect(Collectors.toMap(Field::getName, field -> getAttributeValue(field, object)));
    }

    public <T> List<String> getAttributesNamesFromClass(Class<T> tClass) {
        return getFieldsFromClass(tClass)
                .stream()
                .map(Field::getName)
                .collect(Collectors.toList());
    }

    public Field getAccessibleFieldByName(Object object, String fieldName) throws NoSuchFieldException {
        List<Field> fieldsFromClass = getFieldsFromClass(object.getClass());
        return fieldsFromClass.stream()
                .filter(field -> field.getName().equals(fieldName))
                .peek(field -> field.setAccessible(true))
                .findAny()
                .orElseThrow(() -> new NoSuchFieldException(fieldName));
    }

    private List<Field> getFieldsFromClass(Class<?> tClass) {
        List<Field> superClassFields = new ArrayList<>();
        boolean hasSuperClass = true;
        Class<?> childClass = tClass;
        do {
            Class<?> superclass = childClass.getSuperclass();
            if (Objects.isNull(superclass)) {
                hasSuperClass = false;
                continue;
            }

            superClassFields.addAll(Arrays.asList(superclass.getDeclaredFields()));
            childClass = superclass;
        } while (hasSuperClass);

        List<Field> classFields = Arrays.asList(tClass.getDeclaredFields());
        return Stream.concat(superClassFields.stream(), classFields.stream())
                .collect(Collectors.toList());
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
