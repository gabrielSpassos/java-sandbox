# Postgres JSON Column POC

### Outputs

* By default String attributes are not mapped to JSON columns.
```java
@Column(value = "data")
private String data;

@Column(value = "data_b")
private String binaryData;

Caused by: org.postgresql.util.PSQLException: ERROR: column "data" is of type json but expression is of type character varying
```

* Using own PGobject works properly
```java
@Column(value = "data")
private PGobject data;

@Column(value = "data_b")
private PGobject binaryData;

....

var pgData = new PGobject();
pgData.setType("json|jsonb");
pgData.setValue(objectMapper.writeValueAsString(dataDTO));
```