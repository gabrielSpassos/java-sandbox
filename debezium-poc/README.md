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

### Start
```bash
docker compose up
./start.sh
./register-postgres-connector.sh
./save-order.sh
```

### Clean Up

```bash
docker compose down -v --remove-orphans
```