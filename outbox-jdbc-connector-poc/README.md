# JDBC Source Connector Outbox Pattern PoC

## Overview

This Proof of Concept demonstrates the Outbox Pattern using:

* Java 25
* Spring Boot 4.x
* Spring JDBC
* PostgreSQL
* Apache Kafka
* Kafka Connect JDBC Source Connector
* Docker Compose

---

# Solution Architecture

```text
+-------------------+
| Spring Boot App   |
|-------------------|
| Transactional     |
| business write +  |
| outbox insert     |
+---------+---------+
          |
          v
+-------------------+
| PostgreSQL        |
|-------------------|
| orders            |
| outbox            |
+---------+---------+
          |
          | polling
          v
+-------------------+
| Kafka Connect     |
| JDBC Source       |
| Connector         |
+---------+---------+
          |
          v
+-------------------+
| Apache Kafka      |
|-------------------|
| jdbc-outbox topic |
+-------------------+
```

---

# Project Structure

```text
jdbc-source-outbox-poc/
├── docker-compose.yml
├── jdbc-source.json
├── servers.json
├── pom.xml
├── README.md
└── src/
    └── main/
        ├── java/
        │   └── com/gabrielspassos/
        │       ├── JdbcSourceOutboxApplication.java
        │       ├── controller/
        │       │   └── OrderController.java
        │       ├── service/
        │       │   └── OrderService.java
        │       ├── repository/
        │       │   └── OrderRepository.java
        │       └── model/
        │           ├── Order.java
        │           └── OutboxEvent.java
        └── resources/
            ├── application.yml
            └── schema.sql
```

# schema.sql

```sql
CREATE TABLE orders (
    id UUID PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL
);

CREATE TABLE outbox (
    id UUID PRIMARY KEY,
    aggregate_type VARCHAR(255) NOT NULL,
    aggregate_id UUID NOT NULL,
    event_type VARCHAR(255) NOT NULL,
    payload JSONB NOT NULL,
    created_at TIMESTAMP NOT NULL
);
```

---

# JdbcSourceOutboxApplication.java

```java
package com.gabrielspassos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JdbcSourceOutboxApplication {

    public static void main(String[] args) {
        SpringApplication.run(JdbcSourceOutboxApplication.class, args);
    }
}
```

---

# Order.java

```java
package com.gabrielspassos.model;

import java.time.Instant;
import java.util.UUID;

public record Order(
        UUID id,
        String description,
        Instant createdAt
) {
}
```

---

# OutboxEvent.java

```java
package com.gabrielspassos.model;

import java.time.Instant;
import java.util.UUID;

public record OutboxEvent(
        UUID id,
        String aggregateType,
        UUID aggregateId,
        String eventType,
        String payload,
        Instant createdAt
) {
}
```

---

# OrderRepository.java

```java
package com.gabrielspassos.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@Repository
public class OrderRepository {

    private final JdbcTemplate jdbcTemplate;
    private final ObjectMapper objectMapper;

    public OrderRepository(JdbcTemplate jdbcTemplate,
                           ObjectMapper objectMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.objectMapper = objectMapper;
    }

    @Transactional
    public void createOrder(String description) {
        try {
            UUID orderId = UUID.randomUUID();

            jdbcTemplate.update("""
                INSERT INTO orders(id, description, created_at)
                VALUES (?, ?, ?)
            """,
                    orderId,
                    description,
                    Instant.now()
            );

            String payload = objectMapper.writeValueAsString(Map.of(
                    "orderId", orderId,
                    "description", description
            ));

            jdbcTemplate.update("""
                INSERT INTO outbox(
                    id,
                    aggregate_type,
                    aggregate_id,
                    event_type,
                    payload,
                    created_at
                )
                VALUES (?, ?, ?, ?, ?::jsonb, ?)
            """,
                    UUID.randomUUID(),
                    "Order",
                    orderId,
                    "OrderCreated",
                    payload,
                    Instant.now()
            );

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
```

---

# OrderService.java

```java
package com.gabrielspassos.service;

import com.gabrielspassos.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public void createOrder(String description) {
        repository.createOrder(description);
    }
}
```

---

# OrderController.java

```java
package com.gabrielspassos.controller;

import com.gabrielspassos.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestParam String description) {
        service.createOrder(description);
        return ResponseEntity.ok().build();
    }
}
```

---

# Running the PoC

## 1. Start infrastructure

```bash
docker compose down -v
Docker compose up -d
```

---

## 2. Install JDBC connector

```bash
docker exec -it outbox-kafka-connect bash
```

Inside container:

```bash
confluent-hub install --no-prompt confluentinc/kafka-connect-jdbc:latest
```

Exit container.

Restart connect:

```bash
docker restart outbox-kafka-connect
```

---

## 3. Register connector

```bash
curl -X POST http://localhost:8083/connectors \
  -H "Content-Type: application/json" \
  -d @jdbc-source.json
```

---

## 4. Run Spring Boot application

```bash
./mvnw spring-boot:run
```

---

## 5. Create an order

```bash
curl -X POST "http://localhost:8080/orders?description=test-order"
```

---

# Validate the Flow

## PostgreSQL

Open:

```text
http://localhost:8082
```

Login:

```text
admin@admin.com
admin
```

Check tables:

```sql
SELECT * FROM orders;
SELECT * FROM outbox;
```

---

## Kafka UI

Open:

```text
http://localhost:8081
```

Topic expected:

```text
jdbc-outbox
```

You should see messages generated from the outbox table.

---

# Comparison With Debezium

| Topic          | JDBC Source Connector   | Debezium CDC             |
| -------------- | ----------------------- | ------------------------ |
| Mechanism      | Polling                 | WAL/binlog streaming     |
| Complexity     | Medium                  | High                     |
| Delay          | Poll interval dependent | Near real-time           |
| DB Load        | Polling queries         | Low                      |
| Infrastructure | Kafka Connect           | Kafka Connect + Debezium |
| CDC            | No                      | Yes                      |
| Scalability    | Moderate                | High                     |

---

# Important Notes

## JDBC Connector is NOT CDC

The JDBC Source Connector:

* polls the database
* executes SQL queries repeatedly
* tracks timestamps/incrementing columns

It does not read PostgreSQL WAL logs.

---

# Key Learning Objectives

This PoC helps understand:

* Outbox Pattern
* Kafka Connect
* JDBC Source Connector
* Polling-based integration
* Kafka topic publishing
* Difference between polling and CDC

---

# Recommended Next Steps

After this PoC:

1. Add batching
2. Add Kafka consumers
3. Add retries + DLQ
4. Compare with Debezium CDC
5. Add Testcontainers integration
6. Add Kubernetes deployment
