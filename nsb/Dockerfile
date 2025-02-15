# Etapa de compilación
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa de ejecución
FROM amazoncorretto:17-alpine-jdk
WORKDIR /app
RUN mkdir /app/pdf-files
COPY ./usuarios.json usuarios.json
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]