# For Java 11, try this
FROM adoptopenjdk/openjdk11:alpine-jre

# Refer to Maven build -> finalName
ARG JAR_FILE=build/*.jar

# cd /opt/app
WORKDIR /opt/app

# cp target/*.jar /opt/app/gts-auth-service.jar
COPY ${JAR_FILE} gts-auth-service.jar

# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","gts-auth-service.jar"]