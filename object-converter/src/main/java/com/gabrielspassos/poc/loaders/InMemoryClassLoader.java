package com.gabrielspassos.poc.loaders;

import java.util.Map;

import static java.util.Objects.requireNonNull;

public class InMemoryClassLoader extends ClassLoader {

    private InMemoryFileManager manager;

    public InMemoryClassLoader(ClassLoader parent, InMemoryFileManager manager) {
        super(parent);
        this.manager = requireNonNull(manager, "manager must not be null");
    }

    /*
     this method override the findClass method from the java.lang Class loader
     this is used by the method: protected Class<?> loadClass(String name, boolean resolve)
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        Map<String, JavaSourceFromString> compiledClasses = manager.getBytesMap();

        if (compiledClasses.containsKey(name)) {
            byte[] bytes = compiledClasses.get(name).getBytes();
            return defineClass(name, bytes, 0, bytes.length);
        } else {
            throw new ClassNotFoundException();
        }
    }
}
