## Usage

* Fetch token
```shell
TOKEN=$(curl -s -c cookies.txt http://localhost:8080/csrf | jq -r '.token')
```

* Call transfer api with tokens
```shell
curl -b cookies.txt \
     -H "X-XSRF-TOKEN: $TOKEN" \
     -X POST http://localhost:8080/transfer \
     -d "amount=100"

Transferred: 100
```

* Call transfer apis without token
```
curl -b cookies.txt \
     -X POST http://localhost:8080/transfer \
     -d "amount=100"
     
{"timestamp":"2026-04-19T20:27:14.042Z","status":403,"error":"Forbidden","path":"/transfer"}
```