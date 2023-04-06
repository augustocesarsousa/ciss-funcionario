FROM maven:3.8.6-sapmachine-17 as build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17
WORKDIR /app
COPY target/funcionario-0.0.1-SNAPSHOT.jar /app

CMD ["java", "-jar", "/app/funcionario-0.0.1-SNAPSHOT.jar"]