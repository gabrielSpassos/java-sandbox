package com.gabrielspassos.poc.reflections.services;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClassService extends AbstractClassService {

    private static ClassService instance;

    private ClassService() {
    }

    public static synchronized ClassService getClassService(){
        if (instance == null) {
            instance = new ClassService();
        }
        return instance;
    }

    protected List<Field> getFieldsFromClass(Class<?> tClass) {
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

}
