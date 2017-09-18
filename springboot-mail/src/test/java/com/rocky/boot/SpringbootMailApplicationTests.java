package com.rocky.boot;

import com.rocky.boot.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootMailApplicationTests {

	@Autowired
	private MailService mailService;

	@Test
	public void testSimpleMail() throws Exception {
		mailService.sendSimpleMail("13342437205@163.com","test simple mail"," hello this is simple mail");
	}

	@Test
	public void contextLoads() {
	}

}
