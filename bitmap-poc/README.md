# BitMap POC

## What is?

A bitmap is a compact, indexable data structure that represents a set of boolean 
states using individual bits stored inside machine words, enabling constant-time 
bitwise operations and high memory efficiency.

Instead of storing [true, false, true, false] store like 1010

## Outputs

### Tests

```shell
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running com.gabrielspassos.BitMapTest
[INFO] Tests run: 7, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.123 s -- in com.gabrielspassos.BitMapTest
[INFO] Running SampleTest
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.005 s -- in SampleTest
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 8, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] 
[INFO] --- jar:3.4.1:jar (default-jar) @ bitmap-poc ---
[INFO] Building jar: /home/passos/Documentos/workspace/java-sandbox/bitmap-poc/target/bitmap-poc-1.0-SNAPSHOT.jar
[INFO] 
[INFO] --- install:3.1.2:install (default-install) @ bitmap-poc ---
[INFO] Installing /home/passos/Documentos/workspace/java-sandbox/bitmap-poc/pom.xml to /home/passos/.m2/repository/com/gabrielspassos/bitmap-poc/1.0-SNAPSHOT/bitmap-poc-1.0-SNAPSHOT.pom
[INFO] Installing /home/passos/Documentos/workspace/java-sandbox/bitmap-poc/target/bitmap-poc-1.0-SNAPSHOT.jar to /home/passos/.m2/repository/com/gabrielspassos/bitmap-poc/1.0-SNAPSHOT/bitmap-poc-1.0-SNAPSHOT.jar
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  5.300 s
[INFO] Finished at: 2026-02-12T09:05:43-03:00
[INFO] ------------------------------------------------------------------------
```