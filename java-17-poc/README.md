# Java 17 PoC

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