# Base Alpine Linux based image with OpenJDK JRE only
FROM openjdk:8-jre-alpine
# copy application JAR (with libraries inside)
COPY target/rnr-api-*.jar /app.jar
# specify default command
CMD ["/usr/bin/java", "-jar", "-Dspring.profiles.active=dev", "/app.jar"]
