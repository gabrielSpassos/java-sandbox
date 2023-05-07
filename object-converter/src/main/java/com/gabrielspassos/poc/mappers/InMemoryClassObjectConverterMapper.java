package com.gabrielspassos.poc.mappers;

import com.gabrielspassos.poc.builders.ClassAsStringConverterBuilder;
import com.gabrielspassos.poc.loaders.InMemoryClass;
import com.gabrielspassos.poc.loaders.InMemoryFileManager;
import com.gabrielspassos.poc.loaders.JavaSourceFromString;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;
import java.util.Collections;
import java.util.List;

public class InMemoryClassObjectConverterMapper {

    final static String QUALIFIED_CLASS_NAME_FOLDER = "com.gabrielspassos.poc.loaders.";

    private static InMemoryClassObjectConverterMapper instance;

    private final ClassAsStringConverterBuilder classAsStringConverterBuilder;

    private InMemoryClassObjectConverterMapper() {
        this.classAsStringConverterBuilder = ClassAsStringConverterBuilder.getBuilder();
    }

    public static synchronized InMemoryClassObjectConverterMapper getMapper(){
        if (instance == null) {
            instance = new InMemoryClassObjectConverterMapper();
        }
        return instance;
    }

    public <T> T convert(Object objectToConvert, Class<T> destinyClass) {
        try {
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
            InMemoryFileManager manager = new InMemoryFileManager(compiler
                    .getStandardFileManager(null, null, null));

            String className = classAsStringConverterBuilder
                    .getConverterClassName(objectToConvert, destinyClass);
            String qualifiedName = QUALIFIED_CLASS_NAME_FOLDER + className;
            String converterClassSource = classAsStringConverterBuilder
                    .createConverterClassAsString(className, objectToConvert, destinyClass);

            List<JavaFileObject> sourceFiles = Collections.singletonList(
                    new JavaSourceFromString(qualifiedName, converterClassSource));

            JavaCompiler.CompilationTask task = compiler
                    .getTask(null, manager, diagnostics, null, null, sourceFiles);

            boolean result = task.call();

            if (!result) {
                throw new RuntimeException("error to convert");
            }

            ClassLoader classLoader = manager.getClassLoader(null);
            Class<?> clazz = classLoader.loadClass(qualifiedName);
            InMemoryClass instanceOfClass = (InMemoryClass) clazz.newInstance();

            return (T) instanceOfClass.convert(objectToConvert);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
