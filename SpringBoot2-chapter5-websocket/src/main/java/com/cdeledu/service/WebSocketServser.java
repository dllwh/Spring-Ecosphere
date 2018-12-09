package com.cdeledu.service;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述:
 * 
 *       <pre>
 *   WebSocket是类似客户端服务端的形式(采用ws协议)，那么这里的WebSocketServer其实就相当于一个ws协议的Controller 
 *   直接@ServerEndpoint、@Component启用即可
 *       </pre>
 * 
 *       <pre>
 *ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个 websocket服务器端,
 *注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
 *       </pre>
 * 
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年11月24日 下午6:00:49
 * @版本: V 0.1
 * @since: JDK 1.8
 */
@Slf4j
@Component
@ServerEndpoint(value = "/websocket/{userName}")
public class WebSocketServser {
	// 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
	private static int										onlineCount			= 0;
	// concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
	private static CopyOnWriteArraySet<WebSocketServser>	webSocketSet		= new CopyOnWriteArraySet<WebSocketServser>();
	// 与某个客户端的连接会话，需要通过它来给客户端发送数据
	private Session											session;
	/**
	 * @方法描述 : 连接成功的回调函数
	 * 
	 *       <pre>
	 * 使用@Onopen注解的表示当客户端链接成功后的回掉
	 *       </pre>
	 * 
	 * @param session
	 *            可选参数,session是WebSocket规范中的会话，表示与某个客户端的连接会话，需要通过它来给客户端发送数据
	 */
	@OnOpen
	public void onOpen(Session session,@PathParam(value="userName") String userName) {
		this.session = session;
		webSocketSet.add(this);

		addOnlineCount();
		log.info("【websocket消息】有新连接{}加入！当前在线人数为{}", userName, getOnlineCount());

		/**
		 * 1、给所有人发送上线通知
		 */
		publishAsync("有游客【"+userName+"】加入聊天室!");

		/**
		 * 2、获取在线用户
		 */
	}

	/**
	 * @方法描述 : 连接关闭的回调函数
	 * 
	 *       <pre>
	 *       客户端调用了断开链接方法后才会回调
	 *       </pre>
	 */
	@OnClose
	public void onClose() {
		if(session.isOpen()){
			webSocketSet.remove(this.session);
		}
		
		subOnlineCount();
		publishAsync("【websocket消息】连接断开，当前在线人数为:"+getOnlineCount());
		log.info("【websocket消息】连接断开，当前在线人数为{}", getOnlineCount());
	}

	/**
	 * @方法描述 : 在客户端接收来自客户端消息触发
	 */
	@OnMessage
	public void OnMessage(Session session, String message) {
		log.info("【websocket消息】来自客户端的消息:{},客户端ID是：{}", message, session.getId());
		// 广播信息
		publishAsync(message);
		// 点播信息
	}

	/**
	 * @方法描述 : 在发生错误时触发
	 */
	@OnError
	public void onError(Session sessio, Throwable error) {
		log.error("【websocket消息】发生错误:{}", error.getLocalizedMessage(), error);
	}

	/**
	 * @方法描述 : 服务器主动推送消息(同步消息)
	 * @param message
	 * @throws IOException
	 */
	public void sendMessage(String message) throws IOException {
		this.session.getBasicRemote().sendText(message);
	}

	/**
	 * @方法描述 : 自定义群发消息(异步消息)
	 * @param message
	 * @throws IOException
	 */
	public void publishAsync(String message) {
		log.info("【websocket消息】广播消息, message={}", message);
		for (WebSocketServser item : webSocketSet) {
			if (item.session != session) {
				item.session.getAsyncRemote().sendText(message);
			}
		}
	}
	/**
	 * @方法描述 : 自定义群发消息(同步消息)
	 * @param message
	 * @throws IOException
	 */
	public void publishBasic(String message, String ToUserName) {
		log.info("【websocket消息】广播消息, message={}", message);
		for (WebSocketServser item : webSocketSet) {
			try {
				item.session.getBasicRemote().sendText(message);
			} catch (Exception e) {
			}
		}
	}

	/**
	 * @方法描述 : 获取在线人数
	 * @return
	 */
	public synchronized int getOnlineCount() {
		return onlineCount;
	}

	/**
	 * @方法描述 : 在线人数增加
	 */
	public synchronized void addOnlineCount() {
		WebSocketServser.onlineCount++;
	}

	/**
	 * @方法描述 : 在线人数减少
	 */
	public synchronized void subOnlineCount() {
		WebSocketServser.onlineCount--;
	}
}