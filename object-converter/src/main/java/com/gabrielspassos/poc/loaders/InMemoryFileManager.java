package com.gabrielspassos.poc.loaders;

import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;

public class InMemoryFileManager extends ForwardingJavaFileManager<JavaFileManager> {

    private InMemoryClassLoader loader;

    public InMemoryFileManager(StandardJavaFileManager standardManager) {
        super(standardManager);
        this.loader = new InMemoryClassLoader();
    }

    @Override
    public JavaFileObject getJavaFileForOutput(Location location, String className,
                                               JavaFileObject.Kind kind, FileObject sibling) {

        JavaSourceFromString javaClass = new JavaSourceFromString(className, kind);
        this.loader.addClass(className, javaClass);
        return javaClass;
    }

    @Override
    public ClassLoader getClassLoader(Location location) {
        return loader;
    }

}
