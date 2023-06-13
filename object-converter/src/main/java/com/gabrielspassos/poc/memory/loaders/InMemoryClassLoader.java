package com.gabrielspassos.poc.memory.loaders;

import java.util.Hashtable;
import java.util.Map;

public class InMemoryClassLoader extends ClassLoader {

    private Map<String, JavaSourceFromString> compiledClasses;

    public InMemoryClassLoader() {
        super();
        this.compiledClasses = new Hashtable<>();
    }

    /*
     this method override the findClass method from the java.lang Class loader
     this is used by the method: protected Class<?> loadClass(String name, boolean resolve)
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Map<String, JavaSourceFromString> compiledClasses = this.compiledClasses;

        if (compiledClasses.containsKey(name)) {
            byte[] bytes = compiledClasses.get(name).getBytes();
            return defineClass(name, bytes, 0, bytes.length);
        } else {
            throw new ClassNotFoundException();
        }
    }

    public void addClass(String className, JavaSourceFromString javaClass) {
        compiledClasses.put(className, javaClass);
    }
}
