# Spring Security CSRF Protection PoC

## Overview

This project demonstrates how **CSRF (Cross-Site Request Forgery) protection** works in Spring Security, including:

* Token generation and validation
* Cookie-based vs session-based CSRF
* Real HTTP testing with `curl`
* Common pitfalls and subtle behaviors

---

## Tech Stack

* Java 25
* Spring Boot 4.x
* Spring Security

---

## What is CSRF?

CSRF is an attack where a malicious site tricks a user’s browser into making unintended requests using an authenticated session.

Spring Security prevents this by requiring a **CSRF token** for all state-changing requests (POST, PUT, DELETE).

---

## Security Configuration

Example using cookie-based CSRF:

```java
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
     http
               .authorizeHttpRequests(auth -> auth
                    .anyRequest().permitAll()
               )
               .sessionManagement(session -> session
                    .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
               )
               .csrf(csrf -> csrf
                    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
               );

     return http.build();
}
```

---

## CSRF Token Flow

### 1. Client requests `/csrf`

```bash
curl -c cookies.txt http://localhost:8080/csrf
```

Response:

```json
{
  "headerName": "X-XSRF-TOKEN",
  "parameterName": "_csrf",
  "token": "abc123..."
}
```

Cookies stored:

```
XSRF-TOKEN
JSESSIONID
```

---

### 2. Client sends POST request

```bash
TOKEN=$(curl -s -c cookies.txt http://localhost:8080/csrf | jq -r '.token')

curl -b cookies.txt \
     -H "X-XSRF-TOKEN: $TOKEN" \
     -X POST http://localhost:8080/transfer \
     -d "amount=100"
```

Expected result:

```
HTTP 200 OK
Transferred: 100
```

---

## CSRF Failure Scenarios (403)

### 1. Missing token

```bash
curl -b cookies.txt -X POST http://localhost:8080/transfer -d "amount=100"
```

---

### 2. Invalid token

```bash
curl -b cookies.txt \
     -H "X-XSRF-TOKEN: invalid" \
     -X POST http://localhost:8080/transfer \
     -d "amount=100"
```

---

### 3. Missing session (no cookies)

```bash
curl -H "X-XSRF-TOKEN: $TOKEN" \
     -X POST http://localhost:8080/transfer \
     -d "amount=100"
```

---

### 4. Token from another session

```bash
# session A
TOKEN_A=$(curl -s -c cookiesA.txt http://localhost:8080/csrf | jq -r '.token')

# session B
curl -c cookiesB.txt http://localhost:8080/csrf > /dev/null

# mismatch
curl -b cookiesB.txt \
     -H "X-XSRF-TOKEN: $TOKEN_A" \
     -X POST http://localhost:8080/transfer \
     -d "amount=100"
```

{"timestamp":"2026-04-19T20:27:14.042Z","status":403,"error":"Forbidden","path":"/transfer"}