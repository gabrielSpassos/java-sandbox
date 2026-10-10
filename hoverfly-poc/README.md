# Hoverfly POC

> https://docs.hoverfly.io/projects/hoverfly-java/en/latest/

## Stack

- Java 26
- Spring Boot 4.1.1
- JUnit 5
- Hoverfly Java 0.20.2

## Tests

```
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 6, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] 
[INFO] --- jar:3.5.1:jar (default-jar) @ hoverfly-poc ---
[INFO] Building jar: /home/passos/Documentos/workspace/java-sandbox/hoverfly-poc/target/hoverfly-poc-0.0.1-SNAPSHOT.jar
[INFO] 
[INFO] --- spring-boot:4.1.1:repackage (repackage) @ hoverfly-poc ---
[INFO] Replacing main artifact /home/passos/Documentos/workspace/java-sandbox/hoverfly-poc/target/hoverfly-poc-0.0.1-SNAPSHOT.jar with repackaged archive, adding nested dependencies in BOOT-INF/.
[INFO] The original artifact has been renamed to /home/passos/Documentos/workspace/java-sandbox/hoverfly-poc/target/hoverfly-poc-0.0.1-SNAPSHOT.jar.original
[INFO] 
[INFO] --- install:3.1.4:install (default-install) @ hoverfly-poc ---
[INFO] Installing /home/passos/Documentos/workspace/java-sandbox/hoverfly-poc/pom.xml to /home/passos/.m2/repository/com/gabrielspassos/hoverfly-poc/0.0.1-SNAPSHOT/hoverfly-poc-0.0.1-SNAPSHOT.pom
[INFO] Installing /home/passos/Documentos/workspace/java-sandbox/hoverfly-poc/target/hoverfly-poc-0.0.1-SNAPSHOT.jar to /home/passos/.m2/repository/com/gabrielspassos/hoverfly-poc/0.0.1-SNAPSHOT/hoverfly-poc-0.0.1-SNAPSHOT.jar
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  24.879 s
[INFO] Finished at: 2026-10-10T16:27:49-03:00
[INFO] ------------------------------------------------------------------------
```