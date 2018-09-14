package com.guye.sun.managent.config.webSocket.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * STOMP协议的WebStocket
 */
@Configuration
//注解开启使用STOMP协议来传输基于代理(message broker)的消息,这时控制器支持使用@MessageMapping,就像使用@RequestMapping一样
@EnableWebSocketMessageBroker
public class WebSocketStompConfig extends AbstractWebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {    //注册STOMP协议的节点(endpoint),并映射指定的url

        Logger logger = LoggerFactory.getLogger(WebSocketStompConfig.class);
        registry.addEndpoint("/simple")     //注册一个STOMP的endpoint,并指定使用SockJS协议
                .setAllowedOrigins("*")     //解决跨域问题
                .withSockJS();
        logger.info("注册广播式模式成功");
        registry.addEndpoint("/chating")
                .withSockJS();
        logger.info("注册点对点模式成功");
    }

    //配置消息代理
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        //广播式应配置一个/topic消息代理
        registry.enableSimpleBroker("/topic");

        //点对点式应配置/queue和/topic消息代理
        registry.enableSimpleBroker("/queue","/topic");
    }
}
