package com.rocky.boot.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author rocky
 * Description: 延迟队列配置
 * Created in 2019/4/2
 */
@Configuration
public class DelayConfig {

    @Bean
    public Exchange DelayExchange() {
        return ExchangeBuilder.directExchange("exchange.delay").durable(true).build();
    }

    @Bean
    public Queue s5Queue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", "exchange.dlx-5s");
        args.put("x-message-ttl", 5000);
        return QueueBuilder.durable("queue-5s").withArguments(args).build();
    }

    @Bean
    public Queue s10Queue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", "exchange.dlx-10s");
        args.put("x-message-ttl", 10000);
        return QueueBuilder.durable("queue-10s").withArguments(args).build();
    }

    @Bean
    public Binding s5Binding() {
        return new Binding("queue-5s", Binding.DestinationType.QUEUE, "exchange.delay", "routingkey-5s", null);
    }

    @Bean
    public Binding s10Binding() {
        return new Binding("queue-10s", Binding.DestinationType.QUEUE, "exchange.delay", "routingkey-10s", null);
    }

    @Bean
    public Exchange dlx5sExchange() {
        return ExchangeBuilder.fanoutExchange("exchange.dlx-5s").durable(true).build();
    }

    @Bean
    public Exchange dlx10sExchange() {
        return ExchangeBuilder.fanoutExchange("exchange.dlx-10s").durable(true).build();
    }

    @Bean
    public Queue delay5sQueue() {
        return QueueBuilder.durable("queue-delay-5s").build();
    }

    @Bean
    public Queue delay10sQueue() {
        return QueueBuilder.durable("queue-delay-10s").build();
    }

    @Bean
    public Binding delay5sBinding() {
        return new Binding("queue-delay-5s", Binding.DestinationType.QUEUE, "exchange.dlx-5s", "", null);
    }

    @Bean
    public Binding delay10sBinding() {
        return new Binding("queue-delay-10s", Binding.DestinationType.QUEUE, "exchange.dlx-10s", "", null);
    }
}
