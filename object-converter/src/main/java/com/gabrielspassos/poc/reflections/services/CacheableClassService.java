package com.gabrielspassos.poc.reflections.services;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CacheableClassService extends AbstractClassService {

    private static CacheableClassService instance;

    private HashMap<Class<?>, List<Field>> classFieldsCache;

    private CacheableClassService() {
        this.classFieldsCache = new HashMap<>();
    }

    public static synchronized CacheableClassService getCacheableClassService(){
        if (instance == null) {
            instance = new CacheableClassService();
        }
        return instance;
    }

    protected List<Field> getFieldsFromClass(Class<?> tClass) {
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

}
