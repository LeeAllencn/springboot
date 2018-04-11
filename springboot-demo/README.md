# 项目构建

# 项目结构介绍
- src/main/java 程序开发以及主程序入口
- src/main/resources 配置文件
- src/test/java 测试程序
### spingboot建议的目录
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