FROM openjdk

WORKDIR /app

COPY target/soat-0.0.1-SNAPSHOT.jar /app/soat.jar

ENTRYPOINT [ "java", "-jar", "soat.jar" ]