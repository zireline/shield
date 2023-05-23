FROM openjdk:17

WORKDIR /app

COPY shield-1.0.jar /app

EXPOSE 8080

CMD ["java", "-jar", "shield-1.0.jar"]
