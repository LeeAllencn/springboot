package com.rocky.boot;

import com.rocky.boot.fanout.FanoutSender;
import com.rocky.boot.hello.HelloSender;
import com.rocky.boot.many.LeeSender1;
import com.rocky.boot.many.LeeSender2;
import com.rocky.boot.model.User;
import com.rocky.boot.object.ObjectSender;
import com.rocky.boot.topic.TopicSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRabbitmqApplicationTests {

	@Autowired
	private HelloSender helloSender;

	@Autowired
	private LeeSender1 leeSender1;

	@Autowired
	private LeeSender2 leeSender2;

	@Autowired
	private ObjectSender ObjectSender;

	@Autowired
	private FanoutSender fanoutSender;

	@Autowired
	private TopicSender topicSender;

	/**
	 * 基本测试
	 * @throws Exception
	 */
	@Test
	public void hello() throws Exception {
		helloSender.send();
	}

	@Test
	public void oneToMany() throws Exception {
		for (int i=0;i<100;i++){
			leeSender1.send(i);
		}
	}

	@Test
	public void manyToMany() throws Exception {
		for (int i=0;i<100;i++){
			leeSender1.send(i);
			leeSender2.send(i);
		}
	}

	/**
	 * 发送对象
	 * @throws Exception
	 */
	@Test
	public void sendOject() throws Exception {
		User user=new User();
		user.setName("lee");
		user.setPass("123456");
		ObjectSender.send(user);
	}

	/**
	 * fanout exchange test
	 * @throws Exception
	 */
	@Test
	public void fanoutSender() throws Exception {
		fanoutSender.send();
	}

	@Test
	public void topic() throws Exception {
		topicSender.send();
	}

	@Test
	public void topic1() throws Exception {
		topicSender.send1();
	}

	@Test
	public void topic2() throws Exception {
		topicSender.send2();
	}

	@Test
	public void contextLoads() {
	}

}
