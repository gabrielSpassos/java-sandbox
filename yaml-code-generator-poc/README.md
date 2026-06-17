# YAML Code Generator POC

Small proof of concept for generating Java code from YAML definitions.

The sample definition lives at `src/main/resources/definitions/sample-models.yaml` and generates Java `record` classes into `target/generated-sources/yaml-codegen`.

## Test
```shell
mvn clean install
```

## Run
```shell
mvn clean compile exec:java
```

## YAML shape

```yaml
packageName: com.gabrielspassos.generated
models:
  - name: User
    fields:
      - name: id
        type: Long
      - name: name
        type: String
      - name: birthDate
        type: LocalDate
      - name: createdAt
        type: LocalDateTime
```

### Supported types
- `String`
- `Integer`
- `Long`
- `Double`
- `Boolean`
- `BigDecimal`
- `LocalDate`
- `LocalDateTime`
- `UUID`

## Output

```java
package com.gabrielspassos.generated;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record User(
        Long id,
        String name,
        LocalDate birthDate,
        LocalDateTime createdAt
) {
}
```
