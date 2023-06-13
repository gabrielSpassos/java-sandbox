package com.gabrielspassos.poc.memory;

import com.gabrielspassos.poc.memory.builders.ClassAsStringConverterBuilder;
import com.gabrielspassos.poc.memory.loaders.InMemoryConverterClass;
import com.gabrielspassos.poc.memory.loaders.InMemoryFileManager;
import com.gabrielspassos.poc.memory.loaders.JavaSourceFromString;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class InMemoryClassObjectConverterMapper {

    final static String QUALIFIED_CLASS_NAME_FOLDER = "com.gabrielspassos.poc.memory.loaders.";

    private static InMemoryClassObjectConverterMapper instance;

    private final ClassAsStringConverterBuilder classAsStringConverterBuilder;
    private final HashMap<String, InMemoryConverterClass> convertersMap;


    private InMemoryClassObjectConverterMapper() {
        this.classAsStringConverterBuilder = ClassAsStringConverterBuilder.getBuilder();
        this.convertersMap = new HashMap<>();
    }

    public static synchronized InMemoryClassObjectConverterMapper getMapper(){
        if (instance == null) {
            instance = new InMemoryClassObjectConverterMapper();
        }
        return instance;
    }

    public <T> T convert(Object objectToConvert, Class<T> destinyClass) {
        String converterClassName = classAsStringConverterBuilder.getConverterClassName(objectToConvert, destinyClass);
        InMemoryConverterClass instanceOfClass = convertersMap.get(converterClassName);

        if (Objects.nonNull(instanceOfClass)) {
            return (T) instanceOfClass.convert(objectToConvert);
        }

        instanceOfClass = instantiateConverterClass(converterClassName, objectToConvert, destinyClass);
        convertersMap.put(converterClassName, instanceOfClass);
        return (T) instanceOfClass.convert(objectToConvert);
    }

    private <T> InMemoryConverterClass instantiateConverterClass(String converterClassName,
                                                                 Object objectToConvert,
                                                                 Class<T> destinyClass) {
        try {
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            InMemoryFileManager manager = new InMemoryFileManager(compiler
                    .getStandardFileManager(null, null, null));

            String qualifiedName = QUALIFIED_CLASS_NAME_FOLDER + converterClassName;
            String converterClassSource = classAsStringConverterBuilder
                    .createConverterClassAsString(converterClassName, objectToConvert, destinyClass);
            List<JavaFileObject> sourceFiles = Collections.singletonList(
                    new JavaSourceFromString(qualifiedName, converterClassSource));

            // compile source code
            DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
            JavaCompiler.CompilationTask task = compiler
                    .getTask(null, manager, diagnostics, null, null, sourceFiles);
            boolean result = task.call();
            if (!result) {
                throw new RuntimeException("error to convert");
            }

            // load class
            ClassLoader classLoader = manager.getClassLoader(StandardLocation.CLASS_PATH);
            Class<?> clazz = classLoader.loadClass(qualifiedName);
            return (InMemoryConverterClass) clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
