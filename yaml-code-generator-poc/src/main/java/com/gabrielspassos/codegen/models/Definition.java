package com.gabrielspassos.codegen.models;

import java.util.List;

public record Definition(String packageName, List<ModelDefinition> models) {
}
