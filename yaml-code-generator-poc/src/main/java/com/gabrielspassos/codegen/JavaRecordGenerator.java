package com.gabrielspassos.codegen;

import com.gabrielspassos.codegen.models.FieldDefinition;
import com.gabrielspassos.codegen.models.ModelDefinition;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class JavaRecordGenerator {

    private static final Map<String, String> TYPE_IMPORTS = Map.of(
            "BigDecimal", "java.math.BigDecimal",
            "LocalDate", "java.time.LocalDate",
            "LocalDateTime", "java.time.LocalDateTime",
            "UUID", "java.util.UUID"
    );

    private static final Set<String> JAVA_LANG_TYPES = Set.of(
            "String",
            "Integer",
            "Long",
            "Double",
            "Boolean"
    );

    public String generate(String packageName, ModelDefinition model) {
        String imports = imports(model);
        String fields = model.fields().stream()
                .map(field -> "        " + simpleTypeName(field.type()) + " " + field.name())
                .collect(Collectors.joining(",\n"));

        StringBuilder builder = new StringBuilder();
        builder.append("package ").append(packageName).append(";\n\n");
        if (!imports.isBlank()) {
            builder.append(imports).append("\n");
        }
        builder.append("public record ").append(model.name()).append("(\n")
                .append(fields)
                .append("\n) {\n")
                .append("}\n");

        return builder.toString();
    }

    private static String imports(ModelDefinition model) {
        TreeSet<String> imports = model.fields().stream()
                .map(FieldDefinition::type)
                .filter(type -> !JAVA_LANG_TYPES.contains(type))
                .map(type -> TYPE_IMPORTS.getOrDefault(type, type))
                .filter(type -> type.contains("."))
                .collect(Collectors.toCollection(TreeSet::new));

        return imports.stream()
                .map(type -> "import " + type + ";")
                .collect(Collectors.joining("\n", "", imports.isEmpty() ? "" : "\n"));
    }

    private static String simpleTypeName(String type) {
        int lastPackageSeparator = type.lastIndexOf('.');
        if (lastPackageSeparator == -1) {
            return type;
        }
        return type.substring(lastPackageSeparator + 1);
    }
}
