# Outbox Pattern

> https://microservices.io/patterns/data/transactional-outbox.html

## Usage

* Run docker compose
```bash
docker compose up
```

* Clean up
```bash
docker compose down -v 
```

## Access

* Kafka UI -> http://localhost:8081
* Postgres DB -> http://localhost:8082
  * login: admin@admin.com
  * password: admin

## API
```bash
curl -X POST "http://localhost:8080/orders?description=test-order" \
  -H "Content-Type: application/json" \
  -v
```