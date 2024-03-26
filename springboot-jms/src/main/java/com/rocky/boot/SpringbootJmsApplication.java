package com.rocky.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.core.JmsTemplate;

/**
 * @author rocky
 */
@SpringBootApplication
public class SpringbootJmsApplication implements CommandLineRunner { //1 用于程序启动后执行的代码，通过重写其run方法执行

	/**
	 * 注入JmsTemplate
	 */
	@Autowired
	JmsTemplate jmsTemplate;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJmsApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		// 3 向my-destination目的地发送Msg的消息
		jmsTemplate.send("my-destination", new Msg());
	}
}
