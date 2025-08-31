# Java 23 + Spring + Stress Test + K6

- [x] Java 23
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

## Stress
```bash
docker-compose up --build k6
```

```
  █ THRESHOLDS

    checks
    ✓ 'rate>0.99' rate=100.00%

    http_req_duration
    ✓ 'p(95)<500' p(95)=21.22ms


  █ TOTAL RESULTS

    checks_total.......: 402     7.634979/s
    checks_succeeded...: 100.00% 402 out of 402
    checks_failed......: 0.00%   0 out of 402

    ✓ status is 200
    ✓ response time < 500ms
    ✓ status is 404

    HTTP
    http_req_duration..............: avg=9.37ms  min=2.51ms med=8.69ms  max=31.18ms p(90)=17.01ms p(95)=21.22ms
      { expected_response:true }...: avg=10.77ms min=3.02ms med=10.01ms max=31.18ms p(90)=19.97ms p(95)=22.29ms
    http_req_failed................: 33.33% 67 out of 201
    http_reqs......................: 201    3.817489/s

    EXECUTION
    iteration_duration.............: avg=4.4s    min=2.38s  med=4.34s   max=6.54s   p(90)=5.71s   p(95)=6.16s
    iterations.....................: 67     1.272496/s
    vus............................: 2      min=1         max=10
    vus_max........................: 10     min=10        max=10

    NETWORK
    data_received..................: 45 kB  848 B/s
    data_sent......................: 32 kB  603 B/s




running (0m52.7s), 00/10 VUs, 67 complete and 0 interrupted iterations
default ✓ [ 100% ] 00/10 VUs  50s
```

## Usage

* Load sdkman environment

```bash
sdk env
```

* Run solution

```bash
./run.sh
```

* Run stress test

```bash
./stress.sh
```

* Clean up

```bash
./clean.sh
```

* Prometheus
  * http://localhost:9090

* Prometheus Push Gateway Exporter
  * http://localhost:9091/metrics

* Grafana
  * http://localhost:3000
  * user: admin
  * password: admin
  * https://grafana.com/grafana/dashboards/19665-k6-prometheus/
  ![Dash](src/test/resources/grafana.png)

