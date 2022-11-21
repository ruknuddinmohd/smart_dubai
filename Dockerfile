FROM openjdk:8-jdk-alpine
MAINTAINER smartdubai.ae
COPY /target/bookservice-0.0.1-SNAPSHOT.jar bookservice-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/bookservice-0.0.1-SNAPSHOT.jar"]