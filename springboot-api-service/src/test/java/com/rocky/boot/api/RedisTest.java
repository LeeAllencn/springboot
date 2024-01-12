package com.rocky.boot.api;

import com.rocky.boot.api.model.User;
import com.rocky.boot.api.service.IUserService;
import com.rocky.boot.api.web.response.UserDetailResp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author rocky
 * Description: redis测试类
 * Created in 2021/3/7
 */
@SpringBootTest
public class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisTemplate<String, User> redisTemplate;

    @Autowired
    private IUserService userService;

    @Test
    public void testString() {
        stringRedisTemplate.opsForValue().set("aaa", "111");
        Assertions.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
    }

    @Test
    public void testObject() {
        ValueOperations<String, User> valueOperations = redisTemplate.opsForValue();
        User user = new User();
        UserDetailResp userResp = userService.getUser(1);
        BeanUtils.copyProperties(userResp, user);
        valueOperations.set("com.rocky.boot.api", user);
        Assertions.assertEquals("lee", Objects.requireNonNull(valueOperations.get("com.rocky.boot.api")).getUsername());
    }
}
