package com.rocky.boot;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 *
 * @author Rocky
 * @date 2017-08-18
 */
@Component
public class Receiver {

    /**
     * 指定要监听的目的地
     * @param message message
     */
    @JmsListener(destination = "my-destination")
    public void receiveMessage(String message) {
        System.out.println("接收到： <" + message + ">");
    }
}
