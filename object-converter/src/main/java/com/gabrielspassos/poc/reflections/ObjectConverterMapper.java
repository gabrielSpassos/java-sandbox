package com.gabrielspassos.poc.reflections;

import com.gabrielspassos.poc.reflections.exceptions.ErrorToIncludeValueToAttribute;
import com.gabrielspassos.poc.reflections.exceptions.NoParametersException;
import com.gabrielspassos.poc.reflections.services.ClassService;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ObjectConverterMapper {

    private static ObjectConverterMapper instance;

    private final ClassService classService;

    private ObjectConverterMapper() {
        this.classService = ClassService.getClassService();
    }

    public static synchronized ObjectConverterMapper getObjectConverterMapper(){
        if (instance == null) {
            instance = new ObjectConverterMapper();
        }
        return instance;
    }

    //todo: use bubblesort here
    public <T> T convert(Object objectToConvert, Class<T> destinyClass) {
        if (Objects.isNull(objectToConvert) || Objects.isNull(destinyClass)) {
            throw new NoParametersException();
        }

        Map<Field, Object> attributesNamesAndValues
                = classService.getAttributesAndValuesFromObject(objectToConvert);

        T instanceOfClass = classService.createInstanceOfClass(destinyClass);

        attributesNamesAndValues.forEach((field, attributeVale) ->
                includeValueByAttributeName(instanceOfClass, field, attributeVale));

        return instanceOfClass;
    }

    private <T> void includeValueByAttributeName(T object, Field field, Object attributeValue) {
        try {
            List<Field> fieldsFromObject = classService.getAccessibleFieldsFromObjectByOrigin(object, field);
            for (Field fieldFromObject : fieldsFromObject) {
                fieldFromObject.set(object, attributeValue);
            }
        } catch (NoSuchFieldException e) {
            return;
        } catch (Exception e) {
            throw new ErrorToIncludeValueToAttribute(e, attributeValue, field);
        }
    }

}
