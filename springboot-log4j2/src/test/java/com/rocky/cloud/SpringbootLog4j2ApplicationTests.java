package com.rocky.cloud;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootLog4j2ApplicationTests {

	private static Logger logger = LogManager.getLogger(SpringbootLog4j2ApplicationTests.class);

	@Test
	public void testLog4j2() {
		logger.info("this is info log");
		logger.error("this is error log");
		logger.warn("this is warn log");
	}

	@Test
	public void contextLoads() {
	}

}
