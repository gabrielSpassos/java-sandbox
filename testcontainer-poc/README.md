### Setup MacOs

* Install podman

```shell
brew install podman
podman machine init
podman machine start
```

* Configure podman as rootful
```shell
podman machine stop
podman machine set --rootful
podman machine start
```

* Set env vars
```
export DOCKER_HOST=unix://$(podman machine inspect --format '{{.ConnectionInfo.PodmanSocket.Path}}')
export TESTCONTAINERS_RYUK_DISABLED=true
```

* Run tests
```shell
./tests.sh
```

```shell
➜  testcontainer-poc git:(master) ./tests.sh
[INFO] Scanning for projects...
[INFO] 
[INFO] ----------------< com.gabrielspassos:testcontainer-poc >----------------
[INFO] Building testcontainer-poc 1.0-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-clean-plugin:2.5:clean (default-clean) @ testcontainer-poc ---
[INFO] Deleting /Users/gpassos/Documents/Workspace/java-sandbox/testcontainer-poc/target
[INFO] 
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ testcontainer-poc ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 0 resource
[INFO] 
[INFO] --- maven-compiler-plugin:3.1:compile (default-compile) @ testcontainer-poc ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 3 source files to /Users/gpassos/Documents/Workspace/java-sandbox/testcontainer-poc/target/classes
[WARNING] /Users/gpassos/Documents/Workspace/java-sandbox/testcontainer-poc/src/main/java/com/gabrielspassos/RedisBackedCache.java: /Users/gpassos/Documents/Workspace/java-sandbox/testcontainer-poc/src/main/java/com/gabrielspassos/RedisBackedCache.java uses or overrides a deprecated API.
[WARNING] /Users/gpassos/Documents/Workspace/java-sandbox/testcontainer-poc/src/main/java/com/gabrielspassos/RedisBackedCache.java: Recompile with -Xlint:deprecation for details.
[INFO] 
[INFO] --- maven-resources-plugin:2.6:testResources (default-testResources) @ testcontainer-poc ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 1 resource
[INFO] 
[INFO] --- maven-compiler-plugin:3.1:testCompile (default-testCompile) @ testcontainer-poc ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 2 source files to /Users/gpassos/Documents/Workspace/java-sandbox/testcontainer-poc/target/test-classes
[INFO] 
[INFO] --- maven-surefire-plugin:3.1.2:test (default-test) @ testcontainer-poc ---
[INFO] Using auto detected provider org.apache.maven.surefire.junitplatform.JUnitPlatformProvider
[INFO] 
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running com.gabrielspassos.RedisBackedCacheIntTest
SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
SLF4J: Defaulting to no-operation (NOP) logger implementation
SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 1.119 s -- in com.gabrielspassos.RedisBackedCacheIntTest
[INFO] Running com.gabrielspassos.MySqlConnectorTest
[INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 7.511 s -- in com.gabrielspassos.MySqlConnectorTest
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] 
[INFO] --- maven-jar-plugin:2.4:jar (default-jar) @ testcontainer-poc ---
[INFO] Building jar: /Users/gpassos/Documents/Workspace/java-sandbox/testcontainer-poc/target/testcontainer-poc-1.0-SNAPSHOT.jar
[INFO] 
[INFO] --- maven-install-plugin:2.4:install (default-install) @ testcontainer-poc ---
[INFO] Installing /Users/gpassos/Documents/Workspace/java-sandbox/testcontainer-poc/target/testcontainer-poc-1.0-SNAPSHOT.jar to /Users/gpassos/.m2/repository/com/gabrielspassos/testcontainer-poc/1.0-SNAPSHOT/testcontainer-poc-1.0-SNAPSHOT.jar
[INFO] Installing /Users/gpassos/Documents/Workspace/java-sandbox/testcontainer-poc/pom.xml to /Users/gpassos/.m2/repository/com/gabrielspassos/testcontainer-poc/1.0-SNAPSHOT/testcontainer-poc-1.0-SNAPSHOT.pom
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  10.403 s
[INFO] Finished at: 2024-05-30T10:46:43-03:00
[INFO] ------------------------------------------------------------------------
```