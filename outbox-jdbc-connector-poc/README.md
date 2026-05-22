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

2. Setup JDBC connector
```bash
./setup-jdbc-connector.sh 
```

3. Run Spring Boot application
```bash
./start
```

4. Create an order
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
