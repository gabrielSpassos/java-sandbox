package com.gabrielspassos.codegen;

import java.util.List;

public record Definition(String packageName, List<ModelDefinition> models) {
}
