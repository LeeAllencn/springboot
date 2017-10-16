package com.rocky.boot;

import com.rocky.boot.dao.UserDao;
import com.rocky.boot.entry.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootMongodbApplicationTests {

	@Autowired
	private UserDao userDao;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testSaveUser() throws Exception {
		UserEntity user=new UserEntity();
		user.setId(2l);
		user.setUserName("小明");
		user.setPassWord("fffooo123");
		userDao.saveUser(user);
	}

	@Test
	public void findUserByUserName(){
		UserEntity user= userDao.findUserByUserName("天空");
		System.out.println("user is "+user);
	}

	@Test
	public void updateUser(){
		UserEntity user=new UserEntity();
		user.setId(2l);
		user.setUserName("天空");
		user.setPassWord("fffxxxx");
		userDao.updateUser(user);
	}

	@Test
	public void deleteUserById(){
		userDao.deleteUserById(2l);
	}
}
