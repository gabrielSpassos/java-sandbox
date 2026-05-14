# Debezium POC

### Architecture

```
Spring Boot App
      |
      v
PostgreSQL  ---> Debezium ---> Kafka Topic
                          \
                           -> CDC Events
```

### Links

* Kafka UI: http://localhost:8081/