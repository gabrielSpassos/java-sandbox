package com.gabrielspassos.poc.cacheablereflections;

import com.gabrielspassos.poc.cacheablereflections.exceptions.NoParametersException;
import com.gabrielspassos.poc.cacheablereflections.services.ClassService;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;

public class CacheableReflectionsConverterMapper {

    private static CacheableReflectionsConverterMapper instance;

    private final ClassService classService;

    private CacheableReflectionsConverterMapper() {
        this.classService = ClassService.getClassService();
    }

    public static synchronized CacheableReflectionsConverterMapper getCacheableReflectionsConverterMapper(){
        if (instance == null) {
            instance = new CacheableReflectionsConverterMapper();
        }
        return instance;
    }

    //TODO: create unit tests
    public <T> T convert(Object objectToConvert, Class<T> destinyClass) {
        if (Objects.isNull(objectToConvert) || Objects.isNull(destinyClass)) {
            throw new NoParametersException();
        }

        List<Field> fieldsFromInput = classService.getFieldsFromClass(objectToConvert.getClass());
        List<Field> fieldsFromOutput = classService.getFieldsFromClass(destinyClass);
        T instanceOfClass = classService.createInstanceOfClass(destinyClass);

        for (Field fieldFromOutput : fieldsFromOutput) {
            for (Field fieldFromInput : fieldsFromInput) {
                List<String> fieldWithSynonyms = classService.getFieldWithSynonyms(fieldFromInput);
                if (fieldWithSynonyms.contains(fieldFromOutput.getName())) {
                    try {
                        Object attributeValue = classService.getAttributeValue(fieldFromInput, objectToConvert);
                        fieldFromOutput.setAccessible(true);
                        fieldFromOutput.set(instanceOfClass, attributeValue);
                    } catch (Exception e) {
                        System.out.println("Error to set field " + fieldFromInput);
                    }
                }
            }
        }

        return instanceOfClass;
    }
}
