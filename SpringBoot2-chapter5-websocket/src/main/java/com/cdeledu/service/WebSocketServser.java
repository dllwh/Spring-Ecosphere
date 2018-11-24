package com.cdeledu.service;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: WebSocket是类似客户端服务端的形式(采用ws协议)，
 *       那么这里的WebSocketServer其实就相当于一个ws协议的Controller
 *       直接@ServerEndpoint("/websocket")@Component启用即可
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年11月24日 下午6:00:49
 * @版本: V 0.1
 * @since: JDK 1.8
 */
@Component
@ServerEndpoint(value = "/websocket")
@Slf4j
public class WebSocketServser {
	// 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
	private static int onlineCount = 0;

	// concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
	private static CopyOnWriteArraySet<WebSocketServser> webSocketSet = new CopyOnWriteArraySet<WebSocketServser>();

	// 与某个客户端的连接会话，需要通过它来给客户端发送数据
	private Session session;

	/**
	 * @方法描述 : 建立连接
	 * @param session
	 */
	@OnOpen
	public void onOpen(Session session) {
		this.session = session;
		webSocketSet.add(this);
		addOnlineCount();
		log.info("有新连接加入！当前在线人数为{}", getOnlineCount());
		try {
			sendMessage("连接成功");
		} catch (IOException e) {
			log.error("websocket IO异常");
		}
		sendInfo("有新连接加入");
	}

	/**
	 * @方法描述 : 关闭连接
	 */
	@OnClose
	public void onClose() {
		webSocketSet.remove(this);
		subOnlineCount();
		log.info("有一连接关闭！当前在线人数为{}", getOnlineCount());
		sendInfo("有新连接退出");
	}

	/**
	 * @方法描述 : 收到客户端消息
	 */
	@OnMessage
	public void OnMessage(Session session, String message) {
		log.info("来自客户端的消息:" + message);

		// 群发信息
		sendInfo(message);
	}

	/**
	 * @方法描述 : 发生错误
	 */
	@OnError
	public void onError(Session sessio, Throwable error) {
		error.printStackTrace();
	}

	/**
	 * @方法描述 : 服务器主动推送消息
	 * @param message
	 * @throws IOException
	 */
	public void sendMessage(String message) throws IOException {
		this.session.getBasicRemote().sendText(message);
	}

	/**
	 * @方法描述 : 自定义群发消息
	 * @param message
	 * @throws IOException
	 */
	public static void sendInfo(String message) {
		for (WebSocketServser item : webSocketSet) {
			try {
				item.sendMessage(message);
			} catch (IOException e) {
				continue;
			}
		}
	}

	/**
	 * @方法描述 : 获取在线人数
	 * @return
	 */
	public static synchronized int getOnlineCount() {
		return onlineCount;
	}

	/**
	 * @方法描述 : 在线人数增加
	 */
	public static synchronized void addOnlineCount() {
		WebSocketServser.onlineCount++;
	}

	/**
	 * @方法描述 : 在线人数减少
	 */
	public static synchronized void subOnlineCount() {
		WebSocketServser.onlineCount--;
	}
}
