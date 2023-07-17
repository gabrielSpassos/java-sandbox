package com.gabrielspassos.poc.unsafe.services;

import com.gabrielspassos.poc.common.AttributeSynonym;
import com.gabrielspassos.poc.unsafe.exceptions.ErrorToGetAttributeValueException;
import com.gabrielspassos.poc.unsafe.exceptions.ErrorToInstantiateClassException;
import com.gabrielspassos.poc.unsafe.exceptions.ErrorToPutAttributeValueException;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ClassService {

    private static ClassService instance;

    private final Map<Class<?>, Map<Field, Long>> classFieldsCache;

    private final Unsafe unsafe;

    private ClassService() {
        this.classFieldsCache = new HashMap<>();
        this.unsafe = getUnsafe();
    }

    public static synchronized ClassService getClassService() {
        if (instance == null) {
            instance = new ClassService();
        }
        return instance;
    }

    public Map<Field, Long> getFieldsAndOffsetsFromClass(Class<?> tClass) {
        if (classFieldsCache.containsKey(tClass)) {
            return classFieldsCache.get(tClass);
        }

        Map<Field, Long> fieldsAndOffsetsMap = new HashMap<>();
        boolean hasSuperClass = true;
        Class<?> childClass = tClass;
        do {
            Class<?> superclass = childClass.getSuperclass();
            if (Objects.isNull(superclass)) {
                hasSuperClass = false;
                continue;
            }

            Arrays.stream(superclass.getDeclaredFields())
                    .forEach(field -> {
                        long fieldOffset = unsafe.objectFieldOffset(field);
                        fieldsAndOffsetsMap.put(field, fieldOffset);
                    });
            childClass = superclass;
        } while (hasSuperClass);

        Arrays.stream(tClass.getDeclaredFields())
                .forEach(field -> {
                    long fieldOffset = unsafe.objectFieldOffset(field);
                    fieldsAndOffsetsMap.put(field, fieldOffset);
                });

        classFieldsCache.put(tClass, fieldsAndOffsetsMap);
        return fieldsAndOffsetsMap;
    }

    public <T> T createInstanceOfClass(Class<T> tClass) {
        try {
            return (T) unsafe.allocateInstance(tClass);
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

    public Object getAttributeValue(Field field, Object object, Long offset) {
        try {
            Class<?> type = field.getType();
            if (type.isPrimitive()) {
                if (java.lang.Character.TYPE == type) {
                    return unsafe.getChar(object, offset);
                }

                if (java.lang.Byte.TYPE == type) {
                    return unsafe.getByte(object, offset);
                }

                if (java.lang.Short.TYPE == type) {
                    return unsafe.getShort(object, offset);
                }

                if (java.lang.Integer.TYPE == type) {
                    return unsafe.getInt(object, offset);
                }

                if (java.lang.Long.TYPE == type) {
                    return unsafe.getLong(object, offset);
                }

                if (java.lang.Float.TYPE == type) {
                    return unsafe.getFloat(object, offset);
                }

                if (java.lang.Double.TYPE == type) {
                    return unsafe.getDouble(object, offset);
                }

                if (java.lang.Boolean.TYPE == type) {
                    return unsafe.getBoolean(object, offset);
                }
            }
            return unsafe.getObject(object, offset);
        } catch (Exception e) {
            throw new ErrorToGetAttributeValueException(e, field);
        }
    }

    //todo: test with only the object option
    public void putAttributeValue(Field field, Object object, Long offset, Object attributeValue) {
        try {
            Class<?> type = field.getType();
            if (type.isPrimitive()) {
                if (java.lang.Character.TYPE == type) {
                    unsafe.putChar(object, offset, (Character) attributeValue);
                    return;
                }

                if (java.lang.Byte.TYPE == type) {
                    unsafe.putByte(object, offset, (Byte) attributeValue);
                    return;
                }

                if (java.lang.Short.TYPE == type) {
                    unsafe.putShort(object, offset, (Short) attributeValue);
                    return;
                }

                if (java.lang.Integer.TYPE == type) {
                    unsafe.putInt(object, offset, (Integer) attributeValue);
                    return;
                }

                if (java.lang.Long.TYPE == type) {
                    unsafe.putLong(object, offset, (Long) attributeValue);
                    return;
                }

                if (java.lang.Float.TYPE == type) {
                    unsafe.putFloat(object, offset, (Float) attributeValue);
                    return;
                }

                if (java.lang.Double.TYPE == type) {
                    unsafe.putDouble(object, offset, (Double) attributeValue);
                    return;
                }

                if (java.lang.Boolean.TYPE == type) {
                    unsafe.putBoolean(object, offset, (Boolean) attributeValue);
                    return;
                }
            }
            unsafe.putObject(object, offset, attributeValue);
        } catch (Exception e) {
            throw new ErrorToPutAttributeValueException(e, field);
        }
    }

    private Unsafe getUnsafe() {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            return (Unsafe) f.get(null);
        } catch (Exception e) {
            throw new ErrorToInstantiateClassException(e, "Unsafe");
        }

    }
}
