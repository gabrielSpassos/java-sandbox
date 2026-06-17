package com.gabrielspassos.codegen;

import com.gabrielspassos.codegen.models.Definition;
import com.gabrielspassos.codegen.models.FieldDefinition;
import com.gabrielspassos.codegen.models.ModelDefinition;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class YamlDefinitionLoader {

    public Definition load(Path definitionPath) {
        try (Reader reader = Files.newBufferedReader(definitionPath)) {
            Object yamlData = new Yaml().load(reader);
            return toDefinition(asMap(yamlData, "root"));
        } catch (IOException exception) {
            throw new CodeGenerationException("Could not read YAML definition: " + definitionPath, exception);
        }
    }

    private static Definition toDefinition(Map<?, ?> yamlData) {
        String packageName = requiredString(yamlData, "packageName", "root");
        List<ModelDefinition> models = requiredList(yamlData, "models", "root").stream()
                .map(model -> toModel(asMap(model, "models[]")))
                .toList();

        if (models.isEmpty()) {
            throw new CodeGenerationException("Expected at least one model in 'models'");
        }

        return new Definition(packageName, models);
    }

    private static ModelDefinition toModel(Map<?, ?> modelData) {
        String name = requiredString(modelData, "name", "models[]");
        List<FieldDefinition> fields = requiredList(modelData, "fields", "model '" + name + "'").stream()
                .map(field -> toField(asMap(field, "fields[]")))
                .toList();

        if (fields.isEmpty()) {
            throw new CodeGenerationException("Expected at least one field in model '" + name + "'");
        }

        return new ModelDefinition(name, fields);
    }

    private static FieldDefinition toField(Map<?, ?> fieldData) {
        return new FieldDefinition(
                requiredString(fieldData, "name", "fields[]"),
                requiredString(fieldData, "type", "fields[]")
        );
    }

    private static Map<?, ?> asMap(Object value, String path) {
        if (value instanceof Map<?, ?> map) {
            return map;
        }
        throw new CodeGenerationException("Expected YAML object at " + path);
    }

    private static List<?> requiredList(Map<?, ?> data, String key, String path) {
        Object value = data.get(key);
        if (value instanceof List<?> list) {
            return list;
        }
        throw new CodeGenerationException("Expected list '" + key + "' at " + path);
    }

    private static String requiredString(Map<?, ?> data, String key, String path) {
        Object value = data.get(key);
        if (value instanceof String string && !string.isBlank()) {
            return string;
        }
        throw new CodeGenerationException("Expected string '" + key + "' at " + path);
    }
}
