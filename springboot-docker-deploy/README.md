# deploy steps
```$xslt
本地：
cd springboot-docker-deploy/
mvn package
cd target/
scp springboot-docker-deploy-0.0.1-SNAPSHOT.jar root@192.168.161.134:/root/springboot

虚机：
cd springboot/
todo...
```