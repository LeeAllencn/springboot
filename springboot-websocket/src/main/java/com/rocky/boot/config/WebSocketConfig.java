package com.rocky.boot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * @author rocky
 * 1 开启使用STOMP协议来传输基于代理的消息(EnableWebSocketMessageBroker)
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer{

	@Override
    public void registerStompEndpoints(StompEndpointRegistry registry) { //2 注册STOMP协议的节点（endpoint），并映射到指定的URL
        //3 注册一个STOMP的endpoint，并指定使用SockJS协议
        registry.addEndpoint("/endpointWisely").withSockJS();
        registry.addEndpoint("/endpointChat").withSockJS();
    }


    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) { //4 配置消息代理（Message Broker）
        //5 广播式配置一个/topic消息代理
        registry.enableSimpleBroker("/queue","/topic");
    }

}
