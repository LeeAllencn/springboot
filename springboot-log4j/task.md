# Spring boot中使用log4j记录日志
### 注意事项
1. 在spring-boot-starter中排除spring-boot-starter-logging
2. spring-boot-starter-parent版本在1.4以后要配置
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-log4j2</artifactId>
</dependency>
```
如果配置<artifactId>spring-boot-starter-log4j</artifactId>就会报错