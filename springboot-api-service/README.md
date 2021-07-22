# swagger 访问
http://localhost:8081/swagger-ui.html

# 项目构建

# 项目结构介绍
- src/main/java 程序开发以及主程序入口
- src/main/resources 配置文件
- src/test/java 测试程序
### springboot建议的目录
- 1、Application.java 建议放到根目录下面,主要用于做一些框架配置
- 2、domain目录主要用于实体（Entity）与数据访问层（Repository）
- 3、service 层主要是业务类代码
- 4、controller 负责页面访问控制

# 单元测试

# 开发环境的调试
```
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <optional>true</optional>
    </dependency>
</dependencies>

<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <configuration>
                <fork>true</fork>
            </configuration>
        </plugin>
</plugins>
</build>
```
> [SpringBoot项目在IntelliJ IDEA中实现热部署](http://www.cnblogs.com/winner-0715/p/6666579.html)


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

# 其他
注意：若使用QQ邮箱发送邮件，则需要修改为spring.mail.host=smtp.qq.com，同时spring.mail.password改为QQ邮箱的授权码。
QQ邮箱->设置->账户->POP3/SMTP服务:开启服务后会获得QQ的授权码