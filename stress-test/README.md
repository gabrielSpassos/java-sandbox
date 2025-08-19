# Java 23 + Spring + Stress Test

- [ ] Java 23
- [x] Spring Rest Interface
- [x] Docker container
- [x] Stress test
- [x] Save results in postgres DB
- [x] Use test container to run it tests

## Endpoints

```bash
curl --location 'http://localhost:8080/v1/dungeons' \
--header 'Content-Type: application/json' \
--data '{
    "id": "546cdb04-3281-4602-86c2-8e01647a9271",
    "dungeon": [
        [-2, -3, 3],
        [-5, -10, 1],
        [10, 30, -5]
    ]
}'
```

```bash
curl --location 'http://localhost:8080/v1/dungeons'
```

```bash
curl --location 'http://localhost:8080/v1/dungeons/{{id}}'
```