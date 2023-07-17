package com.gabrielspassos.poc.unsafe;

import com.gabrielspassos.poc.unsafe.exceptions.NoParametersException;
import com.gabrielspassos.poc.unsafe.services.ClassService;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class UnsafeObjectConverterMapper {

    private static UnsafeObjectConverterMapper instance;

    private final ClassService classService;

    private UnsafeObjectConverterMapper() {
        this.classService = ClassService.getClassService();
    }

    public static synchronized UnsafeObjectConverterMapper getUnsafeObjectConverterMapper() {
        if (instance == null) {
            instance = new UnsafeObjectConverterMapper();
        }

        return instance;
    }

    public <T> T convert(Object objectToConvert, Class<T> destinyClass) {
        if (Objects.isNull(objectToConvert) || Objects.isNull(destinyClass)) {
            throw new NoParametersException();
        }

        Map<Field, Long> fieldsFromInput = classService.getFieldsAndOffsetsFromClass(objectToConvert.getClass());
        Map<Field, Long> fieldsFromOutput = classService.getFieldsAndOffsetsFromClass(destinyClass);
        T instanceOfClass = classService.createInstanceOfClass(destinyClass);

        for (Field fieldFromOutput : fieldsFromOutput.keySet()) {
            for (Field fieldFromInput : fieldsFromInput.keySet()) {
                List<String> fieldWithSynonyms = classService.getFieldWithSynonyms(fieldFromInput);
                if (fieldWithSynonyms.contains(fieldFromOutput.getName())) {
                    try {
                        Long fieldFromInputOffset = fieldsFromInput.get(fieldFromInput);
                        Object attributeValue = classService.getAttributeValue(fieldFromInput, objectToConvert, fieldFromInputOffset);

                        Long fieldFromOutputOffset = fieldsFromOutput.get(fieldFromOutput);
                        classService.putAttributeValue(fieldFromOutput, instanceOfClass, fieldFromOutputOffset, attributeValue);
                    } catch (Exception e) {
                        System.out.println("Error to set field " + fieldFromInput);
                    }
                }
            }
        }

        return instanceOfClass;
    }

}
