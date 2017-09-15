package com.rocky.boot;

import com.rocky.boot.model.Person;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRedisApplicationTests {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private RedisTemplate redisTemplate;

	@Test
	public void test() throws Exception {
		stringRedisTemplate.opsForValue().set("aaa", "111");
		Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
	}

	@Test
	public void testObj() throws Exception {
		Person person = new Person("1","lee",25);
		ValueOperations<String, Person> operations=redisTemplate.opsForValue();
		operations.set("com.rocky", person);
		operations.set("com.rocky.boot", person,1, TimeUnit.SECONDS);
		Thread.sleep(1000);
		//redisTemplate.delete("com.rocky.boot");
		boolean exists=redisTemplate.hasKey("com.rocky.boot");
		if(exists){
			System.out.println("exists is true");
		}else{
			System.out.println("exists is false");
		}
		// Assert.assertEquals("aa", operations.get("com.rocky.boot").getUserName());
	}

	@Test
	public void contextLoads() {
	}

}
