FROM openjdk:14

ADD target/taxi-SNAPSHOT app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

EXPOSE 8090