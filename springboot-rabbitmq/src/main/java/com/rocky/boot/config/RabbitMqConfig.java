package com.rocky.boot.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Rocky
 * @date 2017-09-18
 */
@Configuration
public class RabbitMqConfig {

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