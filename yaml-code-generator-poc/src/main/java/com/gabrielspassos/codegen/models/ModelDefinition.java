package com.gabrielspassos.codegen.models;

import java.util.List;

public record ModelDefinition(String name, List<FieldDefinition> fields) {
}
