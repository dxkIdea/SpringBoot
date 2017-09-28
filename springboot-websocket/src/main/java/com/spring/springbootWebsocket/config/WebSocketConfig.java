package com.spring.springbootWebsocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
/**
 *1. @EnableWebSocketMessageBroker注解用于开启使用STOMP协议来传输基于代理（MessageBroker）的消息，
 * 这时候控制器（controller）开始支持@MessageMapping,就像是使用@requestMapping一样。
 * 2.注册一个Stomp的节点（endpoint）,并指定使用SockJS协议
 * 3.配置消息代理（MessageBroker）
 * @author dxk
 *
 */
@Configuration//该类作为spring的xml配置文件中的<beans>，作用为：配置spring容器(应用上下文)
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer{

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		// TODO Auto-generated method stub
		registry.addEndpoint("endpointWisely").withSockJS();
	}
	@Override
	public void configureMessageBroker(MessageBrokerRegistry regisry){
		regisry.enableSimpleBroker("/topic");
	}

}
