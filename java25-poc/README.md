# Java 25 + Spring Boot 3

Simple poc with Java 25 and Spring Boot 3.

* Working integration tests
* Working unit tests with Mockito

### Tests Output

```bash
./mvnw clean install

[INFO] Results:
[INFO]
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO]
[INFO] --- jar:3.4.2:jar (default-jar) @ java25-poc ---
[INFO] Building jar: /mnt/c/Users/gabri/Documents/workspace/java-sandbox/java25-poc/target/java25-poc-0.0.1-SNAPSHOT.jar
[INFO]
[INFO] --- spring-boot:3.5.6:repackage (repackage) @ java25-poc ---
[INFO] Replacing main artifact /mnt/c/Users/gabri/Documents/workspace/java-sandbox/java25-poc/target/java25-poc-0.0.1-SNAPSHOT.jar with repackaged archive, adding nested dependencies in BOOT-INF/.
[INFO] The original artifact has been renamed to /mnt/c/Users/gabri/Documents/workspace/java-sandbox/java25-poc/target/java25-poc-0.0.1-SNAPSHOT.jar.original
[INFO]
[INFO] --- install:3.1.4:install (default-install) @ java25-poc ---
[INFO] Installing /mnt/c/Users/gabri/Documents/workspace/java-sandbox/java25-poc/pom.xml to /home/gabriel/.m2/repository/com/gabrielspassos/poc/java25-poc/0.0.1-SNAPSHOT/java25-poc-0.0.1-SNAPSHOT.pom
[INFO] Installing /mnt/c/Users/gabri/Documents/workspace/java-sandbox/java25-poc/target/java25-poc-0.0.1-SNAPSHOT.jar to /home/gabriel/.m2/repository/com/gabrielspassos/poc/java25-poc/0.0.1-SNAPSHOT/java25-poc-0.0.1-SNAPSHOT.jar
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  38.513 s
[INFO] Finished at: 2025-09-24T11:13:32-03:00
[INFO] ------------------------------------------------------------------------
```