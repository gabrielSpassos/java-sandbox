package com.gabrielspassos.codegen;

import java.nio.file.Path;

public class Main {

    private static final Path DEFAULT_DEFINITION = Path.of("src/main/resources/definitions/sample-models.yaml");
    private static final Path DEFAULT_OUTPUT = Path.of("target/generated-sources/yaml-codegen");

    public static void main(String[] args) {
        Path definitionPath = args.length > 0 ? Path.of(args[0]) : DEFAULT_DEFINITION;
        Path outputDirectory = args.length > 1 ? Path.of(args[1]) : DEFAULT_OUTPUT;

        Definition definition = new YamlDefinitionLoader().load(definitionPath);
        int generatedFiles = new CodeGenerator(new JavaRecordGenerator()).generate(definition, outputDirectory);

        System.out.printf("Generated %d file(s) at %s%n", generatedFiles, outputDirectory.toAbsolutePath());
    }
}
