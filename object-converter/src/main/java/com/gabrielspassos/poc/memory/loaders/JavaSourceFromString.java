package com.gabrielspassos.poc.memory.loaders;

import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.URI;

import static java.util.Objects.requireNonNull;

public class JavaSourceFromString extends SimpleJavaFileObject {

    private String sourceCode;
    protected ByteArrayOutputStream bos = new ByteArrayOutputStream();

    public JavaSourceFromString(String name, String sourceCode) {
        super(
                URI.create("string:///" + name.replace('.', '/') + JavaFileObject.Kind.SOURCE.extension),
                JavaFileObject.Kind.SOURCE
        );
        this.sourceCode = requireNonNull(sourceCode, "sourceCode must not be null");
    }

    public JavaSourceFromString(String name, Kind kind) {
        super(URI.create("string:///" + name.replace('.', '/') + kind.extension), kind);
    }

    /* On SimpleJavaFileObject class this method throw exception every time, the class says
        that subclasses can override the behavior.
     */
    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) {
        return sourceCode;
    }

    @Override
    public OutputStream openOutputStream() {
        return bos;
    }

    public byte[] getBytes() {
        return bos.toByteArray();
    }
}
