package com.gabrielspassos.poc.cacheablereflections.services;

import com.gabrielspassos.poc.common.AttributeSynonym;
import com.gabrielspassos.poc.reflections.exceptions.BasicException;
import com.gabrielspassos.poc.reflections.exceptions.ErrorToGetAttributeValueException;
import com.gabrielspassos.poc.reflections.exceptions.ErrorToInstantiateClassException;
import com.gabrielspassos.poc.reflections.exceptions.InvalidClassConstructorException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClassService {

    private static ClassService instance;

    private final HashMap<Class<?>, List<Field>> classFieldsCache;

    private final HashMap<Class<?>, Constructor<?>> classConstructorsCache;

    private ClassService() {
        this.classFieldsCache = new HashMap<>();
        this.classConstructorsCache = new HashMap<>();
    }

    public static synchronized ClassService getClassService(){
        if (instance == null) {
            instance = new ClassService();
        }
        return instance;
    }

    public List<Field> getFieldsFromClass(Class<?> tClass) {
        if (classFieldsCache.containsKey(tClass)) {
            return classFieldsCache.get(tClass);
        }

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
        List<Field> allFields = Stream.concat(superClassFields.stream(), classFields.stream())
                .collect(Collectors.toList());
        classFieldsCache.put(tClass, allFields);
        return allFields;
    }

    public <T> T createInstanceOfClass(Class<T> tClass) {
        try {
            if (classConstructorsCache.containsKey(tClass)) {
                return (T) classConstructorsCache.get(tClass).newInstance();
            }

            //todo: get if possible get default constructor with one existent method
            Constructor<?>[] constructors = tClass.getConstructors();
            Constructor<?> constructor = Arrays.stream(constructors)
                    .filter(constructorToFilter -> constructorToFilter.getParameterCount() == 0)
                    .findAny()
                    .orElseThrow(() -> new InvalidClassConstructorException(tClass.getName()));

            classConstructorsCache.put(tClass, constructor);
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

        List<String> attributeSynonyms = Arrays.asList(field.getAnnotation(AttributeSynonym.class).synonyms());

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

}
