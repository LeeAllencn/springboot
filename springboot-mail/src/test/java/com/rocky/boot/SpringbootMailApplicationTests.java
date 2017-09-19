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
		mailService.sendSimpleMail("","test simple mail"," hello this is simple mail");
	}

	//TODO
	@Test
	public void testHtmlMail() throws Exception {
		String content="<html>\n" +
				"<body>\n" +
				"    <h3>hello world ! 这是一封Html邮件!</h3>\n" +
				"</body>\n" +
				"</html>";
		mailService.sendHtmlMail("","test simple mail",content);
	}

	//TODO
	@Test
	public void sendAttachmentsMail() {
		String filePath= "images/test.jpg";
		mailService.sendAttachmentsMail("", "主题：带附件的邮件", "有附件，请查收！", filePath);
	}

	//TODO
	@Test
	public void sendInlineResourceMail() {
		String rscId = "lee006";
		String content="<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>";
		String imgPath = "images/test.jpg";

		mailService.sendInlineResourceMail("", "主题：这是有图片的邮件", content, imgPath, rscId);
	}

	@Test
	public void contextLoads() {
	}

}
