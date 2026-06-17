package com.gabrielspassos.codegen;

import com.gabrielspassos.codegen.models.Definition;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CodeGeneratorTest {

    @TempDir
    private Path outputDirectory;

    @Test
    void shouldGenerateJavaRecordsFromYamlDefinition() throws Exception {
        Definition definition = new YamlDefinitionLoader()
                .load(Path.of("src/main/resources/definitions/sample-models.yaml"));

        int generatedFiles = new CodeGenerator(new JavaRecordGenerator())
                .generate(definition, outputDirectory);

        Path generatedUser = outputDirectory.resolve("com/gabrielspassos/generated/User.java");
        Path generatedOrder = outputDirectory.resolve("com/gabrielspassos/generated/Order.java");
        String userContent = Files.readString(generatedUser);
        String orderContent = Files.readString(generatedOrder);

        assertEquals(2, generatedFiles);
        assertTrue(Files.exists(generatedOrder));
        assertTrue(userContent.contains("package com.gabrielspassos.generated;"));
        assertTrue(userContent.contains("import java.time.LocalDate;"));
        assertTrue(userContent.contains("import java.time.LocalDateTime;"));
        assertTrue(userContent.contains("public record User("));
        assertTrue(userContent.contains("Long id"));
        assertTrue(userContent.contains("LocalDateTime createdAt"));
        assertTrue(orderContent.contains("import java.util.UUID;"));
        assertTrue(orderContent.contains("UUID id"));
    }
}
