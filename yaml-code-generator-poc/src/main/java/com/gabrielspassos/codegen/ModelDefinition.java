package com.gabrielspassos.codegen;

import java.util.List;

public record ModelDefinition(String name, List<FieldDefinition> fields) {
}
