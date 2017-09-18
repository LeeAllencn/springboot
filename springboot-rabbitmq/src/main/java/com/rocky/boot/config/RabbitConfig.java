package com.rocky.boot.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Rocky on 2017-09-18.
 */
@Configuration
public class RabbitConfig {

    @Bean
    public Queue Queue() {
        return new Queue("hello");
    }

    @Bean
    public Queue leeQueue() {
        return new Queue("lee");
    }

    @Bean
    public Queue objectQueue() {
        return new Queue("object");
    }
}