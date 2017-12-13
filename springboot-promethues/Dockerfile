FROM docker.io/java:8
ADD springboot-promethues-0.0.1-SNAPSHOT.jar /opt/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/opt/app.jar"]