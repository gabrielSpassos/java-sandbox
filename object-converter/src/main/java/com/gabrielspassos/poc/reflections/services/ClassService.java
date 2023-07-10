package com.gabrielspassos.poc.reflections.services;

import com.gabrielspassos.poc.common.AttributeSynonym;
import com.gabrielspassos.poc.reflections.exceptions.ErrorToGetAttributeValueException;
import com.gabrielspassos.poc.reflections.exceptions.ErrorToInstantiateClassException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    public List<Field> getFieldsFromClass(Class<?> tClass) {
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

    public <T> T createInstanceOfClass(Class<T> tClass) {
        try {
            Constructor<T> constructor = tClass.getConstructor();
            return (T) constructor.newInstance();
        } catch (Exception e) {
            throw new ErrorToInstantiateClassException(e, tClass.getName());
        }
    }

    public List<String> getFieldWithSynonyms(Field field) {
        if (!field.isAnnotationPresent(AttributeSynonym.class)) {
            return List.of(field.getName());
        }

        List<String> attributeSynonyms = getAttributeSynonyms(field);
        return new ArrayList<>() {{
            add(field.getName());
            addAll(attributeSynonyms);
        }};
    }

    public Object getAttributeValue(Field field, Object object) {
        try {
            field.setAccessible(true);
            return field.get(object);
        } catch (IllegalAccessException e) {
            throw new ErrorToGetAttributeValueException(e, field);
        }
    }

    private List<String> getAttributeSynonyms(Field field) {
        return Arrays.asList(field.getAnnotation(AttributeSynonym.class).synonyms());
    }

}
