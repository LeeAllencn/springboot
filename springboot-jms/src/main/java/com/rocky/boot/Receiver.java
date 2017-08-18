package com.rocky.boot;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Created by Rocky on 2017-08-18.
 */
@Component
public class Receiver {

    @JmsListener(destination = "my-destination") //指定要监听的目的地
    public void receiveMessage(String message) {
        System.out.println("接收到： <" + message + ">");
    }
}
