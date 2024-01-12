package com.rocky.boot.function.service.impl;

import com.rocky.boot.function.service.IMailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author : rocky
 * @date : created in 2021/7/21
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceImplTest {

    @Resource
    private IMailService mailService;

    /**
     * 设置收件人邮箱
     */
    private final String to = "example@163.com";

    @Test
    public void sendSimpleMail() {
        mailService.sendSimpleMail(to,"test simple mail 0722"," hello this is simple mail 0722");
    }

    @Test
    public void sendHtmlMail() {
        String content="<html>\n" +
                "<body>\n" +
                "    <h3>hello world ! 这是一封Html邮件!</h3>\n" +
                "</body>\n" +
                "</html>";
        mailService.sendHtmlMail(to,"test simple mail",content);
    }

    @Test
    public void sendAttachmentsMail() {
        String filePath= "C:\\Users\\Administrator\\Desktop\\attach.txt";
        mailService.sendAttachmentsMail(to, "主题：带附件的邮件", "有附件，请查收！", filePath);
    }

    @Test
    public void sendInlineResourceMail() {
        String rscId = "lee006";
        String content= "<html><body>这是有图片的邮件：<img src='cid:" + rscId + "' ></body></html>";
        String imgPath = "C:\\Users\\Administrator\\Desktop\\test.jpg";

        mailService.sendInlineResourceMail(to, "主题：这是有图片的邮件", content, imgPath, rscId);
    }
}