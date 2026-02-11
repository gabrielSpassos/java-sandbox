# Reflections API POC

> Read, invoke, modify

## Outputs
```
Reflections API POC!
Constructor: public com.gabrielspassos.dto.UserDTO()
Constructor: public com.gabrielspassos.dto.UserDTO(java.lang.String,int)
Field: private java.lang.String com.gabrielspassos.dto.UserDTO.name
Field: private int com.gabrielspassos.dto.UserDTO.age
Method: public java.lang.String com.gabrielspassos.dto.UserDTO.getName()
Method: public boolean com.gabrielspassos.dto.UserDTO.equals(java.lang.Object)
Method: public java.lang.String com.gabrielspassos.dto.UserDTO.toString()
Method: public int com.gabrielspassos.dto.UserDTO.hashCode()
Method: public int com.gabrielspassos.dto.UserDTO.getAge()
Method: public boolean com.gabrielspassos.dto.UserDTO.isAdult()
Method: public java.lang.String com.gabrielspassos.dto.UserDTO.greet(java.lang.String)
Method: public boolean com.gabrielspassos.dto.UserDTO.canDrink(java.lang.String,int)
Method: private java.lang.String com.gabrielspassos.dto.UserDTO.hiddenMethod()
User instance by reflections: UserDTO{name='Gabriel', age=28}
User with age 28 isAdult reflections invoke: true
User with name Gabriel greet reflections invoke: Hi, Gabriel
User with age 28 canDrink in USA reflections invoke: true
User with age 28 canDrink in Brazil reflections invoke: true
private hiddenMethod reflections invoke: This is a private method
User with age 18 isAdult reflections invoke: true
User with name ReflectionName greet reflections invoke: Hello, ReflectionName
User with age 18 canDrink in USA reflections invoke: false
User with age 18 canDrink in Brazil reflections invoke: true
```

### Tests 

```shell
[INFO] 
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running SampleTest
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.063 s -- in SampleTest
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] 
[INFO] --- jar:3.4.1:jar (default-jar) @ reflections-api ---
[INFO] Building jar: /home/passos/Documentos/workspace/java-sandbox/reflections-api/target/reflections-api-1.0-SNAPSHOT.jar
[INFO] 
[INFO] --- install:3.1.2:install (default-install) @ reflections-api ---
[INFO] Installing /home/passos/Documentos/workspace/java-sandbox/reflections-api/pom.xml to /home/passos/.m2/repository/com/gabrielspassos/reflections-api/1.0-SNAPSHOT/reflections-api-1.0-SNAPSHOT.pom
[INFO] Installing /home/passos/Documentos/workspace/java-sandbox/reflections-api/target/reflections-api-1.0-SNAPSHOT.jar to /home/passos/.m2/repository/com/gabrielspassos/reflections-api/1.0-SNAPSHOT/reflections-api-1.0-SNAPSHOT.jar
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  7.346 s
[INFO] Finished at: 2026-02-11T08:38:28-03:00
[INFO] ------------------------------------------------------------------------
```