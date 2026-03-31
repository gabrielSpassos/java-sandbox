# Quarkus POC

## Usage

* Run
```shell
mvn clean quarkus:dev
```

* Call API
```shell
curl http://localhost:8080/greet/Gabriel
```

## Outputs
```json
{"message":"Hello Gabriel from Quarkus"}
```

### Tests 

```shell
mvn clean test

[INFO] 
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running com.gabrielspassos.resources.GreetingResourceTest
mar. 31, 2026 8:35:31 AM io.quarkus.bootstrap.runner.Timing printStartupTime
INFO: quarkus-poc 1.0-SNAPSHOT on JVM (powered by Quarkus 3.34.1) started in 5.830s. Listening on: http://localhost:8081
mar. 31, 2026 8:35:31 AM io.quarkus.bootstrap.runner.Timing printStartupTime
INFO: Profile test activated. 
mar. 31, 2026 8:35:31 AM io.quarkus.bootstrap.runner.Timing printStartupTime
INFO: Installed features: [cdi, rest, rest-jackson, smallrye-context-propagation, smallrye-health, vertx]
mar. 31, 2026 8:35:32 AM org.jboss.logmanager.JBossLoggerFinder getLogger
ERROR: The LogManager accessed before the "java.util.logging.manager" system property was set to "org.jboss.logmanager.LogManager". Results may be unexpected.
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 4.264 s -- in com.gabrielspassos.resources.GreetingResourceTest
[INFO] Running SampleTest
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.011 s -- in SampleTest
mar. 31, 2026 8:35:34 AM io.quarkus.bootstrap.runner.Timing printStopTime
INFO: quarkus-poc stopped in 0.043s
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  25.189 s
[INFO] Finished at: 2026-03-31T08:35:34-03:00
[INFO] ------------------------------------------------------------------------
```