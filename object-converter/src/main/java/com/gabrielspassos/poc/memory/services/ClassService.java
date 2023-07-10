package com.gabrielspassos.poc.memory.services;

import com.gabrielspassos.poc.common.AttributeSynonym;

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

    private HashMap<Class<?>, List<Field>> classFieldsCache;

    private ClassService() {
        this.classFieldsCache = new HashMap<>();
    }

    public static synchronized ClassService getInstance(){
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

}
