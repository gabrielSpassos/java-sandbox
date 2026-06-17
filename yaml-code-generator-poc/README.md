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
        type: UUID
      - name: email
        type: String
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
