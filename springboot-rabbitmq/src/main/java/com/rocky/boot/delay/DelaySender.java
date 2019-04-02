package com.rocky.boot.delay;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author rocky
 * Description:
 * Created in 2019/4/1
 */
@Component
public class DelaySender {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send() {
        String context5 = "hello,delay 5s queue";
        System.out.println("Sender : " + context5);
        amqpTemplate.convertAndSend("queue-5s", context5);

        String context10 = "hello,delay 10s queue";
        System.out.println("Sender : " + context10);
        amqpTemplate.convertAndSend("queue-10s", context10);
    }
}
