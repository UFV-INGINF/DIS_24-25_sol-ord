FROM amazoncorretto:17-alpine-jdk
WORKDIR /app
RUN mkdir /app/pdf-files # Create a directory to store the PDF files, according to solution proposed. Not needed according to requriements.
COPY ./usuarios.json usuarios.json
COPY target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]