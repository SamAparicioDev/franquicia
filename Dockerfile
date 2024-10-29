FROM openjdk:latest

RUN mkdir -p /home/app

WORKDIR /home/app

COPY target/*.jar /home/app/app.jar

COPY src/main/resources/application.properties /config/application.properties

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/home/app/app.jar"]