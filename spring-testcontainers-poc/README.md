# Run tests on IntelliJ

Add the following config:
```
-ea --add-opens java.base/java.util=ALL-UNNAMED
```

# Tests

* `./mvnw clean install`
```bash
➜  spring-testcontainers-poc git:(master) ./mvnw clean install
[INFO] Scanning for projects...
[INFO] 
[INFO] ------------< com.gabrielspassos:spring-testcontainers-poc >------------
[INFO] Building spring-testcontainers-poc 0.0.1-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- clean:3.3.2:clean (default-clean) @ spring-testcontainers-poc ---
[INFO] Deleting /Users/gpassos/Documents/Workspace/java-sandbox/spring-testcontainers-poc/target
[INFO] 
[INFO] --- resources:3.3.1:resources (default-resources) @ spring-testcontainers-poc ---
[INFO] Copying 1 resource from src/main/resources to target/classes
[INFO] Copying 0 resource from src/main/resources to target/classes
[INFO] 
[INFO] --- compiler:3.11.0:compile (default-compile) @ spring-testcontainers-poc ---
[INFO] Changes detected - recompiling the module! :source
[INFO] Compiling 11 source files with javac [debug release 21] to target/classes
[INFO] 
[INFO] --- resources:3.3.1:testResources (default-testResources) @ spring-testcontainers-poc ---
[INFO] Copying 1 resource from src/test/resources to target/test-classes
[INFO] 
[INFO] --- compiler:3.11.0:testCompile (default-testCompile) @ spring-testcontainers-poc ---
[INFO] Changes detected - recompiling the module! :dependency
[INFO] Compiling 3 source files with javac [debug release 21] to target/test-classes
[INFO] /Users/gpassos/Documents/Workspace/java-sandbox/spring-testcontainers-poc/src/test/java/com/gabrielspassos/integration/tests/BaseIntegrationTests.java: /Users/gpassos/Documents/Workspace/java-sandbox/spring-testcontainers-poc/src/test/java/com/gabrielspassos/integration/tests/BaseIntegrationTests.java uses unchecked or unsafe operations.
[INFO] /Users/gpassos/Documents/Workspace/java-sandbox/spring-testcontainers-poc/src/test/java/com/gabrielspassos/integration/tests/BaseIntegrationTests.java: Recompile with -Xlint:unchecked for details.
[INFO] 
[INFO] --- surefire:3.1.2:test (default-test) @ spring-testcontainers-poc ---
[INFO] Using auto detected provider org.apache.maven.surefire.junitplatform.JUnitPlatformProvider
[INFO] 
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running com.gabrielspassos.integration.tests.CarIntegrationsTests
20:27:14.526 [main] INFO org.springframework.test.context.support.AnnotationConfigContextLoaderUtils -- Could not detect default configuration classes for test class [com.gabrielspassos.integration.tests.CarIntegrationsTests]: CarIntegrationsTests does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
20:27:14.570 [main] INFO org.springframework.boot.test.context.SpringBootTestContextBootstrapper -- Found @SpringBootConfiguration com.gabrielspassos.SpringTestContainersPocApplication for test class com.gabrielspassos.integration.tests.CarIntegrationsTests
20:27:14.631 [main] INFO org.testcontainers.images.PullPolicy -- Image pull policy will be performed by: DefaultPullPolicy()
20:27:14.632 [main] INFO org.testcontainers.utility.ImageNameSubstitutor -- Image name substitution will be performed by: DefaultImageNameSubstitutor (composite of 'ConfigurationFileImageNameSubstitutor' and 'PrefixingImageNameSubstitutor')
Setting up podman env
20:27:14.901 [main] INFO org.testcontainers.dockerclient.DockerClientProviderStrategy -- Found Docker environment with Environment variables, system properties and defaults. Resolved dockerHost=unix:///var/folders/dq/5vhbc0d51vn01dy58zsr341xpwqf1d/T/podman/podman-machine-default-api.sock
20:27:14.901 [main] INFO org.testcontainers.DockerClientFactory -- Docker host IP address is localhost
20:27:14.937 [main] INFO org.testcontainers.DockerClientFactory -- Connected to docker: 
  Server Version: 5.0.3
  API Version: 1.41
  Operating System: fedora
  Total Memory: 1950 MB
20:27:14.939 [main] WARN org.testcontainers.utility.ResourceReaper -- 
********************************************************************************
Ryuk has been disabled. This can cause unexpected behavior in your environment.
********************************************************************************
20:27:14.940 [main] INFO org.testcontainers.DockerClientFactory -- Checking the system...
20:27:14.940 [main] INFO org.testcontainers.DockerClientFactory -- ✔︎ Docker server version should be at least 1.6.0
20:27:14.969 [main] INFO tc.mysql:latest -- Creating container for image: mysql:latest
20:27:15.065 [main] INFO tc.mysql:latest -- Container mysql:latest is starting: 163cdc6566e299da640c93559017202339114a18f7f99114a6a4698c77a17ffb
20:27:15.224 [main] INFO tc.mysql:latest -- Waiting for database connection to become available at jdbc:mysql://localhost:35913/test using query 'SELECT 1'
20:27:22.265 [main] INFO tc.mysql:latest -- Container mysql:latest started in PT7.296256S
20:27:22.265 [main] INFO tc.mysql:latest -- Container is started (JDBC URL: jdbc:mysql://localhost:35913/test)
20:27:22.271 [main] INFO org.testcontainers.ext.ScriptUtils -- Executing database script from schema.sql
20:27:22.298 [main] INFO org.testcontainers.ext.ScriptUtils -- Executed database script from schema.sql in 26 ms.
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v3.3.0)

2024-06-02T20:27:22.421-03:00  INFO 47322 --- [spring-testcontainers-poc] [           main] c.g.i.tests.CarIntegrationsTests         : Starting CarIntegrationsTests using Java 21 with PID 47322 (started by gpassos in /Users/gpassos/Documents/Workspace/java-sandbox/spring-testcontainers-poc)
2024-06-02T20:27:22.421-03:00  INFO 47322 --- [spring-testcontainers-poc] [           main] c.g.i.tests.CarIntegrationsTests         : No active profile set, falling back to 1 default profile: "default"
2024-06-02T20:27:22.667-03:00  INFO 47322 --- [spring-testcontainers-poc] [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JDBC repositories in DEFAULT mode.
2024-06-02T20:27:22.691-03:00  INFO 47322 --- [spring-testcontainers-poc] [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 21 ms. Found 2 JDBC repository interfaces.
2024-06-02T20:27:22.905-03:00  INFO 47322 --- [spring-testcontainers-poc] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 0 (http)
2024-06-02T20:27:22.910-03:00  INFO 47322 --- [spring-testcontainers-poc] [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2024-06-02T20:27:22.910-03:00  INFO 47322 --- [spring-testcontainers-poc] [           main] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.24]
2024-06-02T20:27:22.932-03:00  INFO 47322 --- [spring-testcontainers-poc] [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2024-06-02T20:27:22.932-03:00  INFO 47322 --- [spring-testcontainers-poc] [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 503 ms
2024-06-02T20:27:22.987-03:00  INFO 47322 --- [spring-testcontainers-poc] [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
2024-06-02T20:27:23.103-03:00  INFO 47322 --- [spring-testcontainers-poc] [           main] com.zaxxer.hikari.pool.HikariPool        : HikariPool-1 - Added connection com.mysql.cj.jdbc.ConnectionImpl@6dc9a56e
2024-06-02T20:27:23.104-03:00  INFO 47322 --- [spring-testcontainers-poc] [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
2024-06-02T20:27:23.376-03:00  INFO 47322 --- [spring-testcontainers-poc] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 63627 (http) with context path '/'
2024-06-02T20:27:23.382-03:00  INFO 47322 --- [spring-testcontainers-poc] [           main] c.g.i.tests.CarIntegrationsTests         : Started CarIntegrationsTests in 1.067 seconds (process running for 9.172)
OpenJDK 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended
WARNING: A Java agent has been loaded dynamically (/Users/gpassos/.m2/repository/net/bytebuddy/byte-buddy-agent/1.14.16/byte-buddy-agent-1.14.16.jar)
WARNING: If a serviceability tool is in use, please run with -XX:+EnableDynamicAgentLoading to hide this warning
WARNING: If a serviceability tool is not in use, please run with -Djdk.instrument.traceUsage for more information
WARNING: Dynamic loading of agents will be disallowed by default in a future release
2024-06-02T20:27:23.920-03:00  INFO 47322 --- [spring-testcontainers-poc] [o-auto-1-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
2024-06-02T20:27:23.920-03:00  INFO 47322 --- [spring-testcontainers-poc] [o-auto-1-exec-1] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
2024-06-02T20:27:23.921-03:00  INFO 47322 --- [spring-testcontainers-poc] [o-auto-1-exec-1] o.s.web.servlet.DispatcherServlet        : Completed initialization in 1 ms
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 9.630 s -- in com.gabrielspassos.integration.tests.CarIntegrationsTests
[INFO] Running com.gabrielspassos.integration.tests.WarehouseIntegrationTests
2024-06-02T20:27:24.078-03:00  INFO 47322 --- [spring-testcontainers-poc] [           main] t.c.s.AnnotationConfigContextLoaderUtils : Could not detect default configuration classes for test class [com.gabrielspassos.integration.tests.WarehouseIntegrationTests]: WarehouseIntegrationTests does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
2024-06-02T20:27:24.079-03:00  INFO 47322 --- [spring-testcontainers-poc] [           main] .b.t.c.SpringBootTestContextBootstrapper : Found @SpringBootConfiguration com.gabrielspassos.SpringTestContainersPocApplication for test class com.gabrielspassos.integration.tests.WarehouseIntegrationTests
Setting up podman env
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.021 s -- in com.gabrielspassos.integration.tests.WarehouseIntegrationTests
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 4, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] 
[INFO] --- jar:3.4.1:jar (default-jar) @ spring-testcontainers-poc ---
[INFO] Building jar: /Users/gpassos/Documents/Workspace/java-sandbox/spring-testcontainers-poc/target/spring-testcontainers-poc-0.0.1-SNAPSHOT.jar
[INFO] 
[INFO] --- install:3.1.2:install (default-install) @ spring-testcontainers-poc ---
[INFO] Installing /Users/gpassos/Documents/Workspace/java-sandbox/spring-testcontainers-poc/pom.xml to /Users/gpassos/.m2/repository/com/gabrielspassos/spring-testcontainers-poc/0.0.1-SNAPSHOT/spring-testcontainers-poc-0.0.1-SNAPSHOT.pom
[INFO] Installing /Users/gpassos/Documents/Workspace/java-sandbox/spring-testcontainers-poc/target/spring-testcontainers-poc-0.0.1-SNAPSHOT.jar to /Users/gpassos/.m2/repository/com/gabrielspassos/spring-testcontainers-poc/0.0.1-SNAPSHOT/spring-testcontainers-poc-0.0.1-SNAPSHOT.jar
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  15.478 s
[INFO] Finished at: 2024-06-02T20:27:28-03:00
[INFO] ------------------------------------------------------------------------
```

* Intellij
```bash
20:28:25.911 [main] INFO org.springframework.test.context.support.AnnotationConfigContextLoaderUtils -- Could not detect default configuration classes for test class [com.gabrielspassos.integration.tests.CarIntegrationsTests]: CarIntegrationsTests does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
20:28:25.961 [main] INFO org.springframework.boot.test.context.SpringBootTestContextBootstrapper -- Found @SpringBootConfiguration com.gabrielspassos.SpringTestContainersPocApplication for test class com.gabrielspassos.integration.tests.CarIntegrationsTests
20:28:26.023 [main] INFO org.testcontainers.images.PullPolicy -- Image pull policy will be performed by: DefaultPullPolicy()
20:28:26.025 [main] INFO org.testcontainers.utility.ImageNameSubstitutor -- Image name substitution will be performed by: DefaultImageNameSubstitutor (composite of 'ConfigurationFileImageNameSubstitutor' and 'PrefixingImageNameSubstitutor')
Setting up podman env
20:28:26.279 [main] INFO org.testcontainers.dockerclient.DockerClientProviderStrategy -- Found Docker environment with Environment variables, system properties and defaults. Resolved dockerHost=unix:///var/folders/dq/5vhbc0d51vn01dy58zsr341xpwqf1d/T/podman/podman-machine-default-api.sock
20:28:26.279 [main] INFO org.testcontainers.DockerClientFactory -- Docker host IP address is localhost
20:28:26.314 [main] INFO org.testcontainers.DockerClientFactory -- Connected to docker: 
  Server Version: 5.0.3
  API Version: 1.41
  Operating System: fedora
  Total Memory: 1950 MB
20:28:26.315 [main] WARN org.testcontainers.utility.ResourceReaper -- 
********************************************************************************
Ryuk has been disabled. This can cause unexpected behavior in your environment.
********************************************************************************
20:28:26.317 [main] INFO org.testcontainers.DockerClientFactory -- Checking the system...
20:28:26.317 [main] INFO org.testcontainers.DockerClientFactory -- ✔︎ Docker server version should be at least 1.6.0
20:28:26.345 [main] INFO tc.mysql:latest -- Creating container for image: mysql:latest
20:28:26.433 [main] INFO tc.mysql:latest -- Container mysql:latest is starting: 376d7dbd48d09c86f10b0cae690615ed045d6ca3613d7324093553e81bf5816b
20:28:26.584 [main] INFO tc.mysql:latest -- Waiting for database connection to become available at jdbc:mysql://localhost:42265/test using query 'SELECT 1'
20:28:33.641 [main] INFO tc.mysql:latest -- Container mysql:latest started in PT7.29577S
20:28:33.641 [main] INFO tc.mysql:latest -- Container is started (JDBC URL: jdbc:mysql://localhost:42265/test)
20:28:33.646 [main] INFO org.testcontainers.ext.ScriptUtils -- Executing database script from schema.sql
20:28:33.668 [main] INFO org.testcontainers.ext.ScriptUtils -- Executed database script from schema.sql in 22 ms.
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v3.3.0)

2024-06-02T20:28:33.781-03:00  INFO 47379 --- [spring-testcontainers-poc] [           main] c.g.i.tests.CarIntegrationsTests         : Starting CarIntegrationsTests using Java 21 with PID 47379 (started by gpassos in /Users/gpassos/Documents/Workspace/java-sandbox/spring-testcontainers-poc)
2024-06-02T20:28:33.782-03:00  INFO 47379 --- [spring-testcontainers-poc] [           main] c.g.i.tests.CarIntegrationsTests         : No active profile set, falling back to 1 default profile: "default"
2024-06-02T20:28:34.032-03:00  INFO 47379 --- [spring-testcontainers-poc] [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JDBC repositories in DEFAULT mode.
2024-06-02T20:28:34.055-03:00  INFO 47379 --- [spring-testcontainers-poc] [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 21 ms. Found 2 JDBC repository interfaces.
2024-06-02T20:28:34.263-03:00  INFO 47379 --- [spring-testcontainers-poc] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 0 (http)
2024-06-02T20:28:34.268-03:00  INFO 47379 --- [spring-testcontainers-poc] [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2024-06-02T20:28:34.269-03:00  INFO 47379 --- [spring-testcontainers-poc] [           main] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.24]
2024-06-02T20:28:34.294-03:00  INFO 47379 --- [spring-testcontainers-poc] [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2024-06-02T20:28:34.294-03:00  INFO 47379 --- [spring-testcontainers-poc] [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 505 ms
2024-06-02T20:28:34.352-03:00  INFO 47379 --- [spring-testcontainers-poc] [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
2024-06-02T20:28:34.478-03:00  INFO 47379 --- [spring-testcontainers-poc] [           main] com.zaxxer.hikari.pool.HikariPool        : HikariPool-1 - Added connection com.mysql.cj.jdbc.ConnectionImpl@5b0e9e0c
2024-06-02T20:28:34.478-03:00  INFO 47379 --- [spring-testcontainers-poc] [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
2024-06-02T20:28:34.791-03:00  INFO 47379 --- [spring-testcontainers-poc] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 63759 (http) with context path '/'
2024-06-02T20:28:34.797-03:00  INFO 47379 --- [spring-testcontainers-poc] [           main] c.g.i.tests.CarIntegrationsTests         : Started CarIntegrationsTests in 1.115 seconds (process running for 9.467)
WARNING: A Java agent has been loaded dynamically (/Users/gpassos/.m2/repository/net/bytebuddy/byte-buddy-agent/1.14.16/byte-buddy-agent-1.14.16.jar)
WARNING: If a serviceability tool is in use, please run with -XX:+EnableDynamicAgentLoading to hide this warning
WARNING: If a serviceability tool is not in use, please run with -Djdk.instrument.traceUsage for more information
WARNING: Dynamic loading of agents will be disallowed by default in a future release
OpenJDK 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended
2024-06-02T20:28:35.350-03:00  INFO 47379 --- [spring-testcontainers-poc] [o-auto-1-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
2024-06-02T20:28:35.350-03:00  INFO 47379 --- [spring-testcontainers-poc] [o-auto-1-exec-1] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
2024-06-02T20:28:35.350-03:00  INFO 47379 --- [spring-testcontainers-poc] [o-auto-1-exec-1] o.s.web.servlet.DispatcherServlet        : Completed initialization in 0 ms
2024-06-02T20:28:35.496-03:00  INFO 47379 --- [spring-testcontainers-poc] [           main] t.c.s.AnnotationConfigContextLoaderUtils : Could not detect default configuration classes for test class [com.gabrielspassos.integration.tests.WarehouseIntegrationTests]: WarehouseIntegrationTests does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
2024-06-02T20:28:35.496-03:00  INFO 47379 --- [spring-testcontainers-poc] [           main] .b.t.c.SpringBootTestContextBootstrapper : Found @SpringBootConfiguration com.gabrielspassos.SpringTestContainersPocApplication for test class com.gabrielspassos.integration.tests.WarehouseIntegrationTests
Setting up podman env
2024-06-02T20:28:36.379-03:00  INFO 47379 --- [spring-testcontainers-poc] [ionShutdownHook] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown initiated...
2024-06-02T20:28:36.386-03:00  INFO 47379 --- [spring-testcontainers-poc] [ionShutdownHook] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown completed.

Process finished with exit code 0
```