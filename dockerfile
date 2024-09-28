FROM amazoncorretto:22-alpine-jdk
VOLUME /calendly
COPY target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]