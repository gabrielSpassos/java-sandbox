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
  
* Host localhost:8080 was resolved.
* IPv6: ::1
* IPv4: 127.0.0.1
*   Trying [::1]:8080...
* Connected to localhost (::1) port 8080
> POST /orders?description=test-order HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/8.5.0
> Accept: */*
> Content-Type: application/json
> 
< HTTP/1.1 200 
< Content-Length: 0
< Date: Wed, 06 May 2026 00:20:10 GMT
< 
* Connection #0 to host localhost left intact
```