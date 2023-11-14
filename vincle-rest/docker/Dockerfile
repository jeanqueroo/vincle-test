# For Java 8, try this
# FROM openjdk:8-jdk-alpine

# For Java 17, try this
FROM openjdk:17-alpine

# Refer to Maven build -> finalName
ARG JAR_FILE=target/vincle-0.0.1-SNAPSHOT.jar

# cd /opt/app
WORKDIR /opt/app

# cp target/vincle.jar /opt/app/app.jar
COPY ${JAR_FILE} app.jar

# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","app.jar"]

## docker build -t vincle:1.0 . -f docker/Dockerfile
## sudo docker run -p 8080:8080 -t vincle:1.0
## sudo docker run -p 80:8080 -t vincle:1.0
## sudo docker run -p 443:8443 -t vincle:1.0