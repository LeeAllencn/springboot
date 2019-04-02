package com.rocky.boot.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author rocky
 * Description: 死信队列
 * Created in 2019/3/29
 */
@Configuration
public class DlxConfig {

    /**
     * 创建一个普通交换器
     * @return
     */
    @Bean
    public Exchange normalExchange() {
        return ExchangeBuilder.fanoutExchange("exchange.normal").durable(true).build();
    }

    /**
     * 创建一个普通队列
     * 当消息处理失败时，通过配置死信交换器将消息发送到死信队列
     * @return
     */
    @Bean
    public Queue normalQueue() {
        Map<String, Object> args = new HashMap<>();
        // 声明死信交换器
        args.put("x-dead-letter-exchange", "exchange.dlx");
        // 声明死信路由键
        args.put("x-dead-letter-routing-key", "routingkey.dlx");
        // 设置消息过期时间，消息过期后，会重新发布到DLX
        args.put("x-message-ttl", 10000);
        return QueueBuilder.durable("queue.normal").withArguments(args).build();
    }

    /**
     * 普通队列绑定
     * @return
     */
    @Bean
    public Binding normalBinding() {
        return new Binding("queue.normal", Binding.DestinationType.QUEUE, "exchange.normal", "routingkey.normal", null);
    }

    /**
     * 创建一个死信交换器
     * @return
     */
    @Bean
    public Exchange deadLetterExchange() {
        return ExchangeBuilder.directExchange("exchange.dlx").durable(true).build();
    }

    /**
     * 创建一个死信队列
     * @return
     */
    @Bean
    public Queue deadLetterQueue() {
        return QueueBuilder.durable("queue.dlx").build();
    }

    /**
     * 死信队列绑定
     * @return
     */
    @Bean
    public Binding deadLetterBinding() {
        return new Binding("queue.dlx", Binding.DestinationType.QUEUE, "exchange.dlx", "routingkey.dlx", null);
    }
}
