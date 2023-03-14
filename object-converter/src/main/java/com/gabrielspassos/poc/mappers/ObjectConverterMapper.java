package com.gabrielspassos.poc.mappers;

import com.gabrielspassos.poc.exceptions.BasicException;
import com.gabrielspassos.poc.exceptions.ErrorToIncludeValueToAttribute;
import com.gabrielspassos.poc.exceptions.ErrorToInstantiateClassException;
import com.gabrielspassos.poc.exceptions.InvalidClassConstructorException;
import com.gabrielspassos.poc.exceptions.NoParametersException;
import com.gabrielspassos.poc.services.CacheableClassService;
import com.gabrielspassos.poc.services.ClassService;
import com.gabrielspassos.poc.services.IClassService;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
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

        Map<String, Object> attributesNamesAndValues
                = getClassService().getAttributesNamesAndValuesFromObject(objectToConvert);

        T instanceOfClass = createInstanceOfClass(destinyClass);

        attributesNamesAndValues.forEach((key, value) -> includeValueByAttributeName(instanceOfClass, key, value));

        return instanceOfClass;
    }

    public void setShouldCacheClassInfo(Boolean shouldCacheClassInfo) {
        this.shouldCacheClassInfo = shouldCacheClassInfo;
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
            Field field = getClassService().getAccessibleFieldByName(object, attributeName);
            field.set(object, attributeValue);
        } catch (NoSuchFieldException e) {
            return;
        } catch (Exception e) {
            throw new ErrorToIncludeValueToAttribute(e, attributeValue, attributeName);
        }
    }

    private IClassService getClassService() {
        return this.shouldCacheClassInfo ? this.cacheableClassService : this.classService;
    }
}
