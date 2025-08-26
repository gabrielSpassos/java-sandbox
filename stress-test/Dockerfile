FROM openjdk:23-jdk-slim
ADD /target/stress-test-*.jar /stress-test.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/stress-test.jar"]
