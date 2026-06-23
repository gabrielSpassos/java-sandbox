# Spring Boot Graph DB POC

## Graph
```
Alice ----KNOWS----> Bob ----KNOWS----> Charlie
   |                    |
   |                    |
WORKS_AT             WORKS_AT
   |                    |
Acme Inc.           Acme Inc.
        |
   LOCATED_IN
        |
    San Francisco

Alice --LIVES_IN--> San Francisco
Bob   --LIVES_IN--> New York
Charlie --LIVES_IN--> Boston
```

## Tests

```bash
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 7, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] 
[INFO] --- jar:3.5.0:jar (default-jar) @ spring-graphdb ---
[INFO] Building jar: /home/passos/Documentos/workspace/java-sandbox/spring-graphdb/target/spring-graphdb-0.0.1-SNAPSHOT.jar
[INFO] 
[INFO] --- spring-boot:4.1.0:repackage (repackage) @ spring-graphdb ---
[INFO] Replacing main artifact /home/passos/Documentos/workspace/java-sandbox/spring-graphdb/target/spring-graphdb-0.0.1-SNAPSHOT.jar with repackaged archive, adding nested dependencies in BOOT-INF/.
[INFO] The original artifact has been renamed to /home/passos/Documentos/workspace/java-sandbox/spring-graphdb/target/spring-graphdb-0.0.1-SNAPSHOT.jar.original
[INFO] 
[INFO] --- install:3.1.4:install (default-install) @ spring-graphdb ---
[INFO] Installing /home/passos/Documentos/workspace/java-sandbox/spring-graphdb/pom.xml to /home/passos/.m2/repository/com/gabrielspassos/spring-graphdb/0.0.1-SNAPSHOT/spring-graphdb-0.0.1-SNAPSHOT.pom
[INFO] Installing /home/passos/Documentos/workspace/java-sandbox/spring-graphdb/target/spring-graphdb-0.0.1-SNAPSHOT.jar to /home/passos/.m2/repository/com/gabrielspassos/spring-graphdb/0.0.1-SNAPSHOT/spring-graphdb-0.0.1-SNAPSHOT.jar
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  54.423 s
[INFO] Finished at: 2026-06-18T23:25:55-03:00
[INFO] ------------------------------------------------------------------------
```