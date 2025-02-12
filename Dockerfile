FROM openjdk:17-alpine
ARG JAR_FILE=build/libs/hope-0.0.1.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]