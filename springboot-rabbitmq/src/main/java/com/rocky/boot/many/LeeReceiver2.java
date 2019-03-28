package com.rocky.boot.many;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 *
 * @author Rocky
 * @date 2017-09-18
 */
@Component
@RabbitListener(queues = "lee")
public class LeeReceiver2 {
    @RabbitHandler
    public void process(String lee) {
        System.out.println("Receiver 2: " + lee);
    }
}
