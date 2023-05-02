package com.gabrielspassos.poc.loaders;

import com.gabrielspassos.poc.mappers.FileClassObjectConverterMapper;

public class MyClassLoader extends ClassLoader {

    private final FileClassObjectConverterMapper fileClassObjectConverterMapper;

    public MyClassLoader(ClassLoader parent) {
        super(parent);
        this.fileClassObjectConverterMapper = FileClassObjectConverterMapper.getMapper();
    }

    public Class customLoadClass(Object objectToConvert, Class<?> destinyClass) {
        try {
            String className = fileClassObjectConverterMapper.getConverterClassName(objectToConvert, destinyClass);
            String converterClassAsString = fileClassObjectConverterMapper.createConverterClassAsString(className, objectToConvert, destinyClass);
            //generate .class from converterClassAsString
            byte[] classData = converterClassAsString.getBytes();
            return defineClass(className, classData, 0, classData.length);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
