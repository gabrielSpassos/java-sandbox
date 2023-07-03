package com.gabrielspassos.poc.reflections.services;

import com.gabrielspassos.poc.common.AttributeSynonym;
import com.gabrielspassos.poc.reflections.exceptions.BasicException;
import com.gabrielspassos.poc.reflections.exceptions.ErrorToGetAttributeValueException;
import com.gabrielspassos.poc.reflections.exceptions.ErrorToInstantiateClassException;
import com.gabrielspassos.poc.reflections.exceptions.InvalidClassConstructorException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
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

    public Map<Field, Object> getAttributesAndValuesFromObject(Object object) {
        return getFieldsFromClass(object.getClass())
                .stream()
                .filter(field -> Objects.nonNull(getAttributeValue(field, object))) //todo: validate why has this filter
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

    public <T> T createInstanceOfClass(Class<T> tClass) {
        try {
            Constructor<?>[] constructors = tClass.getConstructors(); //todo: include cache
            Constructor<?> constructor = Arrays.stream(constructors)
                    .filter(constructorToFilter -> constructorToFilter.getParameterCount() == 0)
                    .findAny()
                    .orElseThrow(() -> new InvalidClassConstructorException(tClass.getName()));
            return (T) constructor.newInstance();
        } catch (BasicException e) {
            throw e;
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

    private List<String> getAttributeSynonyms(Field field) {
        return Arrays.asList(field.getAnnotation(AttributeSynonym.class).synonyms());
    }

    private Predicate<Field> isFieldExistentOnValidNamesList(List<String> validFieldNames) {
        return fieldFromObject -> validFieldNames.stream()
                .anyMatch(validFieldName -> validFieldName.equals(fieldFromObject.getName()));
    }
}
