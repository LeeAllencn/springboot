# web开发
## json 接口开发
使用@RestController注解，默认类中的方法都会以json的格式返回
## 自定义Filter

## 自定义Property
```yaml
com:
  lee:
    title: rocky
    type: blog    
```
```java
@org.springframework.stereotype.Component
public class Demo{
    @Value("${com.lee.title}")
    private String title;
    @Value("${com.lee.type}")
    private String type;
}
```
## log配置
```
spring boot 默认使用Logback日志框架
格式：
logging.path="日志输出地址"
logging.level.包名=级别(eg.INFO/DEBUG/ERROR)



```
# 数据库操作

# thymeleaf模板

# Gradle 构建工具

# WebJars