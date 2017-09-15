#基镜像为java，版本为8
FROM java:8

#作者为lee
MAINTAINER lee

#将jar包添加到镜像中，并重命名为app.jar
ADD springboot-docker-deploy-0.0.1-SNAPSHOT.jar app.jar

#运行镜像的容器，监听8080端口
EXPOSE 8080

#启动时运行 java -jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]