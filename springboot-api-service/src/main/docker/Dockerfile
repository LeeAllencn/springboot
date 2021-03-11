# 定义基础镜像
FROM java:8

# 声明作者
MAINTAINER rocky

# 运行镜像的容器，监听80端口
EXPOSE 80

# 设置时区
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

ENV APP_HOME=/springboot/springboot-api-service
RUN mkdir -p $APP_HOME/

# 设置CMD指明的命令的运行目录
WORKDIR $APP_HOME/

# 设置容器可以访问宿主机上的目录
VOLUME $APP_HOME/

COPY target/springboot-api-service-*.jar $APP_HOME/springboot-api-service.jar
COPY src/main/resources/application-dev.yml $APP_HOME/application-dev.yml
COPY src/main/resources/application-stg.yml $APP_HOME/application-stg.yml
COPY src/main/resources/application-prd.yml $APP_HOME/application-prd.yml

ENV JAVA_OPTS="-Xmx1024m -Xms512m"
ENV LANG=en_US.UTF-8

# 启动运行
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar springboot-api-service.jar" ]