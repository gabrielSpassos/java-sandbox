FROM openjdk:23
ADD /target/stress-test-*.jar /stress-test.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/stress-test.jar"]
