# Java 23 + Spring + Stress Test

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

```
Generating reports...

================================================================================
---- Global Information --------------------------------------------------------
> request count                                       2295 (OK=2295   KO=0     )
> min response time                                      2 (OK=2      KO=-     )
> max response time                                    494 (OK=494    KO=-     )
> mean response time                                     9 (OK=9      KO=-     )
> std deviation                                         20 (OK=20     KO=-     )
> response time 50th percentile                          6 (OK=6      KO=-     )
> response time 75th percentile                         11 (OK=11     KO=-     )
> response time 95th percentile                         23 (OK=23     KO=-     )
> response time 99th percentile                         45 (OK=45     KO=-     )
> mean requests/sec                                 23.182 (OK=23.182 KO=-     )
---- Response Time Distribution ------------------------------------------------
> t < 800 ms                                          2295 (100%)
> 800 ms <= t < 1200 ms                                  0 (  0%)
> t >= 1200 ms                                           0 (  0%)
> failed                                                 0 (  0%)
================================================================================
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
  * http://localhost:9000

* Grafana
  * http://localhost:3000
  * user: admin
  * password: admin

* Graphite Exporter
  * http://localhost:9108/metrics
