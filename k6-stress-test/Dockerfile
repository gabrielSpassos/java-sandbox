FROM openjdk:23-jdk-slim
ADD /target/k6-stress-test-*.jar /k6-stress-test.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/k6-stress-test.jar"]
