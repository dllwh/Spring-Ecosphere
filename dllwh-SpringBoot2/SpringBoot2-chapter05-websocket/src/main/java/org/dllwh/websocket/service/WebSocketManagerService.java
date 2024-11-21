package org.dllwh.websocket.service;

import java.util.Map;

import org.dllwh.model.WebSocketInfo;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 管理websocket的session
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2020-01-22
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
public interface WebSocketManagerService {
	/**
	 * 
	 * @方法描述: 根据标识获取websocket session
	 * @param identifier 标识
	 * @return WebSocket
	 */
	WebSocketInfo get(String identifier);

	/**
	 * 
	 * @方法描述: 放入一个 websocket session
	 * @param identifier 标识
	 * @param webSocket  websocket
	 */
	void put(String identifier, WebSocketInfo webSocket);

	/**
	 * @方法描述: 删除
	 * @param identifier 标识
	 */
	void remove(String identifier);

	/**
	 * @方法描述: 获取当前保存的WebSocket
	 *
	 * @return
	 */
	Map<String, WebSocketInfo> localWebSocketMap();

	/**
	 * @方法描述: 统计所有在线人数
	 * @return 所有在线人数
	 */
	default int size() {
		return localWebSocketMap().size();
	}

	/**
	 * @方法描述: 在OnMessage中判断是否是心跳, 从客户端的消息判断是否是ping消息
	 * @param identifier 标识
	 * @param message    消息
	 * @return 是否是ping消息
	 */
	default boolean isPing(String identifier, String message) {
		return "ping".equalsIgnoreCase(message);
	}

	/**
	 * @方法描述: 返回心跳信息
	 * @param identifier 标识
	 * @param message    消息
	 * @return 返回的pong消息
	 */
	default String pong(String identifier, String message) {
		return "pong";
	}
}
