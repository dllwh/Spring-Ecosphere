package com.cdeledu.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 开启webSocket支持
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年10月27日 下午8:32:30
 * @版本: V1.0
 * @since: JDK 1.8
 */
@Configuration
@EnableWebSocketMessageBroker // 开启使用STOMP协议来传输消息
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
	/**
	 * @方法描述 : ServerEndpointExporter 用于扫描和注册所有携带 ServerEndPoint 注解的实例，若部署到外部容器
	 *       则无需提供此类。
	 * @return
	 */
	@Bean
	public ServerEndpointExporter serverEndpointExporter() {
		return new ServerEndpointExporter();
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		// 注册一个stomp的节点，使用SockJS协议
		registry.addEndpoint("/customendpoint").withSockJS();
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		// 使用内置的消息代理进行订阅和广播；路由消息的目标头以“/topic”或“/queue”开头。
		config.enableSimpleBroker("/topic", "/queue");
	}
}