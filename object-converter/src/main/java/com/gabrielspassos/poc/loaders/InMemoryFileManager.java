package com.gabrielspassos.poc.loaders;

import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import java.util.Hashtable;
import java.util.Map;

public class InMemoryFileManager extends ForwardingJavaFileManager<JavaFileManager> {

    private Map<String, JavaClassAsBytes> compiledClasses;
    private ClassLoader loader;

    public InMemoryFileManager(StandardJavaFileManager standardManager) {
        super(standardManager);
        this.compiledClasses = new Hashtable<>();
        this.loader = new InMemoryClassLoader(this.getClass().getClassLoader(), this);
    }

    @Override
    public JavaFileObject getJavaFileForOutput(Location location, String className,
                                               JavaFileObject.Kind kind, FileObject sibling) {

        JavaClassAsBytes classAsBytes = new JavaClassAsBytes(className, kind);
        compiledClasses.put(className, classAsBytes);

        return classAsBytes;
    }

    @Override
    public ClassLoader getClassLoader(Location location) {
        return loader;
    }

    public Map<String, JavaClassAsBytes> getBytesMap() {
        return compiledClasses;
    }
}
