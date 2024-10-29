FROM bellsoft/liberica-openjdk-alpine:latest
LABEL authors="yamaxila"
ARG JAR_FILE=runner/target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]