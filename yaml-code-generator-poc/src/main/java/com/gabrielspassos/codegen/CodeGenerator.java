package com.gabrielspassos.codegen;

import com.gabrielspassos.codegen.models.Definition;
import com.gabrielspassos.codegen.models.ModelDefinition;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CodeGenerator {

    private final JavaRecordGenerator javaRecordGenerator;

    public CodeGenerator(JavaRecordGenerator javaRecordGenerator) {
        this.javaRecordGenerator = javaRecordGenerator;
    }

    public int generate(Definition definition, Path outputDirectory) {
        Path packageDirectory = outputDirectory.resolve(definition.packageName().replace('.', '/'));
        createDirectory(packageDirectory);

        int generatedFiles = 0;
        for (ModelDefinition model : definition.models()) {
            Path outputFile = packageDirectory.resolve(model.name() + ".java");
            writeFile(outputFile, javaRecordGenerator.generate(definition.packageName(), model));
            generatedFiles++;
        }

        return generatedFiles;
    }

    private static void createDirectory(Path packageDirectory) {
        try {
            Files.createDirectories(packageDirectory);
        } catch (IOException exception) {
            throw new CodeGenerationException("Could not create package directory: " + packageDirectory, exception);
        }
    }

    private static void writeFile(Path outputFile, String content) {
        try {
            Files.writeString(outputFile, content);
        } catch (IOException exception) {
            throw new CodeGenerationException("Could not write generated file: " + outputFile, exception);
        }
    }
}
