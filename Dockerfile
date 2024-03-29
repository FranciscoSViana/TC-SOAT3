FROM openjdk:17

WORKDIR /app

COPY target/soat-0.0.1-SNAPSHOT.jar /app/soat.jar

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "soat.jar" ]