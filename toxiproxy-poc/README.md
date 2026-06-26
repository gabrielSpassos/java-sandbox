# Toxiproxy POC (Chaos Test)

```
Application
    |
    |
Toxiproxy
    |
    |
PostgreSQL
```

## Tests

```
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] 
[INFO] --- jar:3.5.0:jar (default-jar) @ toxiproxy-poc ---
[INFO] Building jar: /home/passos/Documentos/workspace/java-sandbox/toxiproxy-poc/target/toxiproxy-poc-0.0.1-SNAPSHOT.jar
[INFO] 
[INFO] --- spring-boot:4.1.0:repackage (repackage) @ toxiproxy-poc ---
[INFO] Replacing main artifact /home/passos/Documentos/workspace/java-sandbox/toxiproxy-poc/target/toxiproxy-poc-0.0.1-SNAPSHOT.jar with repackaged archive, adding nested dependencies in BOOT-INF/.
[INFO] The original artifact has been renamed to /home/passos/Documentos/workspace/java-sandbox/toxiproxy-poc/target/toxiproxy-poc-0.0.1-SNAPSHOT.jar.original
[INFO] 
[INFO] --- install:3.1.4:install (default-install) @ toxiproxy-poc ---
[INFO] Installing /home/passos/Documentos/workspace/java-sandbox/toxiproxy-poc/pom.xml to /home/passos/.m2/repository/com/gabrielspassos/toxiproxy-poc/0.0.1-SNAPSHOT/toxiproxy-poc-0.0.1-SNAPSHOT.pom
[INFO] Installing /home/passos/Documentos/workspace/java-sandbox/toxiproxy-poc/target/toxiproxy-poc-0.0.1-SNAPSHOT.jar to /home/passos/.m2/repository/com/gabrielspassos/toxiproxy-poc/0.0.1-SNAPSHOT/toxiproxy-poc-0.0.1-SNAPSHOT.jar
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  25.213 s
[INFO] Finished at: 2026-06-26T09:12:22-03:00
[INFO] ------------------------------------------------------------------------
```