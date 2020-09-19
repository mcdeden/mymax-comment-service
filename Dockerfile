FROM openjdk:8-jdk-alpine
MAINTAINER mcdeden <mcdeden@gmail.com>
VOLUME /tmp
EXPOSE 8311
ADD target/*.jar Comment.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/Comment.jar"]