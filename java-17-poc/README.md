# Java 17 PoC

### Usage
* Build
````shell
./gradlew clean build
````
* Execute
````shell
java --enable-preview -jar build/libs/java-17-poc-1.0-SNAPSHOT.jar
````

### Notes

- `sealed` classes must be at same package
- must make explicit to enable Java 17 preview features at `build.gradle`


```groovy
sourceCompatibility = JavaVersion.VERSION_17
targetCompatibility = JavaVersion.VERSION_17

...

tasks.withType(JavaCompile) {
  options.compilerArgs += "--enable-preview"
}

tasks.withType(Test) {
jvmArgs += "--enable-preview"
}

tasks.withType(JavaExec) {
jvmArgs += '--enable-preview'
}
```

- Compiler alerts with sealed missing class on switch
<img width="506" alt="Screen Shot 2023-01-05 at 22 04 12" src="https://user-images.githubusercontent.com/32275521/210909282-c55f6566-e183-4b32-a176-8e9db503a914.png">
