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

## Solution Architecture

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

## Running the PoC

1. Start infrastructure

```bash
docker compose down -v --remove-orphans
Docker compose up
```

2. Install JDBC connector

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

3. Register connector
```bash
curl -X POST http://localhost:8083/connectors \
  -H "Content-Type: application/json" \
  -d @jdbc-source.json
```

4. Run Spring Boot application
```bash
./start
```

5. Create an order
```bash
./save-order.sh
```

## Comparison With Debezium

| Topic          | JDBC Source Connector   | Debezium CDC             |
| -------------- | ----------------------- | ------------------------ |
| Mechanism      | Polling                 | WAL/binlog streaming     |
| Complexity     | Medium                  | High                     |
| Delay          | Poll interval dependent | Near real-time           |
| DB Load        | Polling queries         | Low                      |
| Infrastructure | Kafka Connect           | Kafka Connect + Debezium |
| CDC            | No                      | Yes                      |
| Scalability    | Moderate                | High                     |
