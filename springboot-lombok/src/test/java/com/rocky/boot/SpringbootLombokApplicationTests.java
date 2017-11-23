package com.rocky.boot;

import com.rocky.boot.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SpringbootLombokApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void testLombok()
	{
		//测试Getter/Setter
		User user = new User();
		user.setName("测试lombok");
		user.setAge(10);
		System.out.println(user.getName());
		System.out.println(user.getAge());
		System.out.println(user.toString());
		log.info(user.toString());
	}
}
