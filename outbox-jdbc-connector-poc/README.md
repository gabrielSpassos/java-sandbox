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
docker compose up
```

2. Setup JDBC connector
```bash
./setup-jdbc-connector.sh 
```

```
======================================
Registering JDBC Source Connector
======================================
{"name":"outbox-source","config":{"connector.class":"io.confluent.connect.jdbc.JdbcSourceConnector","tasks.max":"1","connection.url":"jdbc:postgresql://postgres:5432/outbox-jdbc","connection.user":"postgres","connection.password":"postgres","mode":"timestamp","timestamp.column.name":"created_at","table.whitelist":"outbox-jdbc","topic.prefix":"jdbc-","poll.interval.ms":"5000","value.converter":"org.apache.kafka.connect.json.JsonConverter","value.converter.schemas.enable":"false","name":"outbox-source"},"tasks":[],"type":"source"}
```

3. Run Spring Boot application
```bash
./start
```

4. Create an order
```bash
./save-order.sh
```

5. Query table
```bash
docker exec -it outbox-postgres psql -U postgres -d outbox-jdbc
outbox-jdbc=# select * from orders;
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

## Links
* Kafka UI: http://localhost:8081/