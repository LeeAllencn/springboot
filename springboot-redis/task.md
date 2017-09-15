# redis
### 自动根据方法生成缓存
```
直接在方法上添加@Cacheable(value="person-key")注解
```
注：其中value的值就是缓存到redis中的key

# spring session
```
1、引入依赖
<dependency>
    <groupId>org.springframework.session</groupId>
    <artifactId>spring-session-data-redis</artifactId>
</dependency>

2、Session配置：
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 86400*30)
public class SessionConfig {
}

3、测试
@RequestMapping("/uid")
String uid(HttpSession session) {
    UUID uid = (UUID) session.getAttribute("uid");
    if (uid == null) {
        uid = UUID.randomUUID();
    }
    session.setAttribute("uid", uid);
    return session.getId();
}
登录redis 输入 keys '*session*'
登录http://localhost:8080/uid
对比sessionId是否一致，如果发现一致，就说明session已经在redis里面进行有效的管理了。
```
###如何在两台或者多台中共享session
其实就是按照上面的步骤在另一个项目中再次配置一次，启动后自动就进行了session共享。