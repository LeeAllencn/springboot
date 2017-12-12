FROM hub.hcpaas.com/lixinc01/java:8
COPY . /usr/src/app
WORKDIR /usr/src/app
RUN mvn package
CMD ["java", "-jar", "target/springboot-promethues-0.1.0-SNAPSHOT.jar"]