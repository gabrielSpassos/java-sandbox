package com.gabrielspassos.poc.services;

import com.gabrielspassos.poc.exceptions.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ConverterService {

    private static ConverterService instance;

    private final ClassService classService;

    private ConverterService() {
        this.classService = ClassService.getClassService();
    }

    public static synchronized ConverterService getConverterService(){
        if (instance == null) {
            instance = new ConverterService();
        }
        return instance;
    }

    public <T> T convert(Object objectToConvert, Class<T> destinyClass) {
        if (Objects.isNull(objectToConvert) || Objects.isNull(destinyClass)) {
            throw new NoParametersException();
        }

        Map<String, Object> attributesNamesAndValues
                = classService.getAttributesNamesAndValuesFromObject(objectToConvert);
        System.out.println(attributesNamesAndValues);

        List<String> attributesNamesFromClass = classService.getAttributesNamesFromClass(destinyClass);
        System.out.println(attributesNamesFromClass);

        T instanceOfClass = createInstanceOfClass(destinyClass);
        System.out.println(instanceOfClass);

        attributesNamesAndValues.forEach((key, value) -> includeValueByAttributeName(instanceOfClass, key, value));

        return instanceOfClass;
    }

    private <T> T createInstanceOfClass(Class<T> tClass) {
        try {
            Constructor<?>[] constructors = tClass.getConstructors();
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

    private <T> void includeValueByAttributeName(T object, String attributeName, Object attributeValue) {
        try {
            Field field = classService.getAccessibleFieldByName(object, attributeName);
            field.set(object, attributeValue);
        } catch (NoSuchFieldException e) {
            return;
        } catch (Exception e) {
            throw new ErrorToIncludeValueToAttribute(e, attributeValue, attributeName);
        }
    }
}
