FROM openjdk:11
ARG JAR_FILE=target/*.jar
ARG CSV_DIR=prices/
COPY ${JAR_FILE} app.jar
COPY ${CSV_DIR} prices
ENTRYPOINT ["java","-jar","/app.jar"]