FROM adoptopenjdk:8-jre-hotspot
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]
EXPOSE 8080
EXPOSE 5432
