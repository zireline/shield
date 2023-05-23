FROM openjdk:17

WORKDIR /app

# Build the application using Maven
RUN mvn clean package

COPY target/**.jar /app

EXPOSE 8080

CMD ["java", "-jar", "**.jar"]
