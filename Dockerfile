# URL :https://www.callicoder.com/spring-boot-docker-example/

# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add Maintainer Info
LABEL maintainer="Sapient"
 
# Make port 8091 available to the world outside this container
EXPOSE 8091
  
# Add a volume pointing to /tmp
VOLUME /tmp

# The application's jar file
ARG JAR_FILE=target/inventory-service-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} websocket-demo.jar
  
# Run the jar file 
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/websocket-demo.jar"]
