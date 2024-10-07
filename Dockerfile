FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/api_pizzaria.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
