package com.gabrielspassos.poc.reflections;

import com.gabrielspassos.poc.reflections.exceptions.BasicException;
import com.gabrielspassos.poc.reflections.exceptions.ErrorToIncludeValueToAttribute;
import com.gabrielspassos.poc.reflections.exceptions.ErrorToInstantiateClassException;
import com.gabrielspassos.poc.reflections.exceptions.InvalidClassConstructorException;
import com.gabrielspassos.poc.reflections.exceptions.NoParametersException;
import com.gabrielspassos.poc.reflections.services.CacheableClassService;
import com.gabrielspassos.poc.reflections.services.ClassService;
import com.gabrielspassos.poc.reflections.services.IClassService;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ObjectConverterMapper {

    private static ObjectConverterMapper instance;

    private final ClassService classService;

    private final CacheableClassService cacheableClassService;

    private Boolean shouldCacheClassInfo;

    private ObjectConverterMapper() {
        this.classService = ClassService.getClassService();
        this.cacheableClassService = CacheableClassService.getCacheableClassService();
        this.shouldCacheClassInfo = true;
    }

    public static synchronized ObjectConverterMapper getObjectConverterMapper(){
        if (instance == null) {
            instance = new ObjectConverterMapper();
        }
        return instance;
    }

    public <T> T convert(Object objectToConvert, Class<T> destinyClass) {
        if (Objects.isNull(objectToConvert) || Objects.isNull(destinyClass)) {
            throw new NoParametersException();
        }

        Map<Field, Object> attributesNamesAndValues
                = getClassService().getAttributesAndValuesFromObject(objectToConvert);

        T instanceOfClass = getClassService().createInstanceOfClass(destinyClass);

        attributesNamesAndValues.forEach((field, attributeVale) ->
                includeValueByAttributeName(instanceOfClass, field, attributeVale));

        return instanceOfClass;
    }

    public void setShouldCacheClassInfo(Boolean shouldCacheClassInfo) {
        this.shouldCacheClassInfo = shouldCacheClassInfo;
    }

    private <T> void includeValueByAttributeName(T object, Field field, Object attributeValue) {
        try {
            List<Field> fieldsFromObject = getClassService().getAccessibleFieldsFromObjectByOrigin(object, field);
            for (Field fieldFromObject : fieldsFromObject) {
                fieldFromObject.set(object, attributeValue);
            }
        } catch (NoSuchFieldException e) {
            return;
        } catch (Exception e) {
            throw new ErrorToIncludeValueToAttribute(e, attributeValue, field);
        }
    }

    private IClassService getClassService() {
        return this.shouldCacheClassInfo ? this.cacheableClassService : this.classService;
    }
}
