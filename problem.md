### 1. Spring Boot - 让人抓狂的ClassNotFoundException
```
如：
java.lang.NoClassDefFoundError: javax/servlet/http/HttpServletResponse
Caused by: java.lang.ClassNotFoundException: javax.servlet.http.HttpServletResponse

解决方案：
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-tomcat</artifactId>
</dependency>
```
