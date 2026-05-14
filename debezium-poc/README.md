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
