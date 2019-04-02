package com.rocky.boot.dlx;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author rocky
 * Description:
 * Created in 2019/4/1
 */
@Component
public class DlxSender {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send() {
        String context = "hello,Dead Letter Exchange";
        System.out.println("Sender : " + context);
        amqpTemplate.convertAndSend("queue.normal", context);
    }
}
