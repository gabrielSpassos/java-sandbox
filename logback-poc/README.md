# Logback POC

### Endpoints

1. Create Person
Request:
```bash
curl --location --request POST 'http://localhost:8080/v1/people'
```
Response: 
HTTP Status 201 (Created)
```json
{
    "name": "Gabriel",
    "lastName": "Passos"
}
```

2. Get People (returns error on purpose)
Request:
```bash
curl --location 'http://localhost:8080/v1/people'
```
Response:
HTTP Status 417 (Expectation Failed)
```json
{
  "message": "Test purpose error to get people",
  "code": "ERR-001",
  "subErrors": []
}
```