package com.rocky.boot.many;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by Rocky on 2017-09-18.
 */
@Component
@RabbitListener(queues = "lee")
public class LeeReceiver1 {
    @RabbitHandler
    public void process(String lee) {
        System.out.println("Receiver 1: " + lee);
    }
}
