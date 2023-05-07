package com.gabrielspassos.poc.mappers;

import com.gabrielspassos.poc.builders.ClassAsStringConverterBuilder;
import com.gabrielspassos.poc.services.CacheableClassService;
import com.gabrielspassos.poc.services.IClassService;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class FileClassObjectConverterMapper {

    private static FileClassObjectConverterMapper instance;

    private IClassService classService;

    private ClassAsStringConverterBuilder classAsStringConverterBuilder;

    private FileClassObjectConverterMapper() {
        this.classService = CacheableClassService.getCacheableClassService();
        this.classAsStringConverterBuilder = ClassAsStringConverterBuilder.getBuilder();
    }

    public static synchronized FileClassObjectConverterMapper getMapper(){
        if (instance == null) {
            instance = new FileClassObjectConverterMapper();
        }
        return instance;
    }

    public <T> T convert(Object objectToConvert, Class<T> destinyClass) {
        // source file
        File sourceFile = createFileWithSourceCode(objectToConvert, destinyClass);
        String classname = getClassNameFromFile(sourceFile);

        // compile the source file
        File parentDirectory = compileSourceFile(sourceFile);

        // load the compiled class
        Class<?> converterClass = loadClassIntoClassLoader(parentDirectory, classname);

        // call a method on the loaded class
        Method convertMethod = getMethod(converterClass, "convert", objectToConvert.getClass());
        Object convertedClassInstance = classService.createInstanceOfClass(converterClass);
        //Object convertedClassInstance = converterClass.newInstance();
        Object invoke = invokeMethod(convertMethod, convertedClassInstance, objectToConvert);
        return (T) invoke;
    }

    private File createFileWithSourceCode(Object objectToConvert, Class<?> destinyClass) {
        try {
            String converterClassName = classAsStringConverterBuilder.getConverterClassName(objectToConvert, destinyClass);

            // create an empty source file
            //File sourceFile = File.createTempFile(converterClassName, ".java");
            File sourceFile = createFileWithNotExists(converterClassName + ".java");
            sourceFile.deleteOnExit();

            // generate the source code, using the source filename as the class name
            String classname = getClassNameFromFile(sourceFile);
            String sourceCode = classAsStringConverterBuilder.createConverterClassAsString(classname, objectToConvert, destinyClass);

            // write the source code into the source file
            FileWriter writer = new FileWriter(sourceFile);
            writer.write(sourceCode);
            writer.close();

            return sourceFile;
        } catch (Exception e) {
            throw new RuntimeException(e); //todo
        }
    }

    private File compileSourceFile(File sourceFile) {
        try {
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
            File parentDirectory = sourceFile.getParentFile();
            fileManager.setLocation(StandardLocation.CLASS_OUTPUT, Collections.singletonList(parentDirectory));
            Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(List.of(sourceFile));
            compiler.getTask(null, fileManager, null, null, null, compilationUnits).call();
            fileManager.close();

            return parentDirectory;
        } catch (Exception e) {
            throw new RuntimeException(e); //todo
        }
    }

    private Class<?> loadClassIntoClassLoader(File parentDirectory, String classname) {
        try {
            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { parentDirectory.toURI().toURL() });
            return classLoader.loadClass(classname);
        } catch (Exception e) {
            throw new RuntimeException(e); //todo
        }
    }

    private String getClassNameFromFile(File sourceFile) {
        return sourceFile.getName().split("\\.")[0];
    }

    private Method getMethod(Class<?> classToGetMethod, String methodName, Class<?>... parameterTypes) {
        try{
            return classToGetMethod.getDeclaredMethod(methodName, parameterTypes);
        } catch (Exception e) {
            throw new RuntimeException(e); //todo
        }
    }

    private Object invokeMethod(Method methodToInvoke, Object instanceToUseToInvoke, Object... args) {
        try {
            return methodToInvoke.invoke(instanceToUseToInvoke, args);
        } catch (Exception e) {
            throw new RuntimeException(e); //todo
        }
    }

    private File createFileWithNotExists(String filename) {
        try {
            Path source = Paths.get(this.getClass().getResource("/mappers").getPath());
            String finalFileName = String.format("%s/%s", source, filename);
            File file = new File(finalFileName);

            if(!file.exists()){
                file.createNewFile();
            }

            return file;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
