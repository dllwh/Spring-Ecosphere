package org.dllwh.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
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
// @EnableWebSocketMessageBroker // 开启使用STOMP协议来传输基于代理的消息，支持使用@MessageMapping(类似于@RequestMapping)
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

	/**
	 * 注册STOMP协议节点，并映射指定的URL
	 * 
	 * @see registry 添加STOMP协议的端点。提供WebSocket或SockJS客户端访问的地址
	 * @see withSockJS：使用SockJS协议
	 */
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		// 注册一个stomp的节点，使用SockJS协议
		registry.addEndpoint("/customendpoint").setAllowedOrigins("*") // 添加允许跨域访问
				.withSockJS();
	}

	/**
	 * 配置消息代理 启动Broker，消息的发送的地址符合配置的前缀来的消息才发送到这个broker
	 */
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		// 使用内置的消息代理进行订阅和广播；路由消息的目标头以“/topic”或“/queue”开头。
		registry.enableSimpleBroker("/topic", "/queue"); // 推送消息前缀
		// registry.setApplicationDestinationPrefixes(""); // 应用请求前缀
		// registry.setUserDestinationPrefix(""); // 推送用户前缀
	}
}