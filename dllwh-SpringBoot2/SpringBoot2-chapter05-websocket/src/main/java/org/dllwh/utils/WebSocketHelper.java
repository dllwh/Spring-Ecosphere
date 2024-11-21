package org.dllwh.utils;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Future;

import javax.websocket.Session;

import lombok.extern.slf4j.Slf4j;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: TODO(这里用一句话描述这个类的作用)
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2020-01-22
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Slf4j
public final class WebSocketHelper {
	/**
	 * @方法描述: 向特定的用户发送消息(同步消息)
	 *
	 * @param session
	 * @param message
	 * @return
	 */
	public static boolean sendMessage(Session session, String message) {
		boolean isOk = true;
		try {
			session.getBasicRemote().sendText(message);
		} catch (IOException e) {
			isOk = false;
			log.error("发送消息时异常：" + e.getMessage(), e);
		}
		return isOk;
	}

	/**
	 * @方法描述: 向特定的用户异步发送消息
	 *
	 * @param session
	 * @param message
	 * @return
	 */
	public static boolean sendMessageAsync(Session session, String message) {
		Future<Void> voidFuture = session.getAsyncRemote().sendText(message);
		return voidFuture.isDone();
	}

	/**
	 * @方法描述: 向特定的用户发送对象消息
	 *
	 * @param session
	 * @param object
	 * @return
	 */
	public static boolean sendObject(Session session, Object object) {
		boolean isOk = true;
		try {
			session.getBasicRemote().sendObject(object);
			return true;
		} catch (Exception e) {
			isOk = false;
			log.error("发送消息时异常：" + e.getMessage(), e);
		}
		return isOk;
	}

	/**
	 * @方法描述: 向特定的用户异步发送对象
	 *
	 * @param session
	 * @param object
	 * @return
	 */
	public static boolean sendObjectAsync(Session session, Object object) {
		Future<Void> voidFuture = session.getAsyncRemote().sendObject(object);
		return voidFuture.isDone();
	}

	/**
	 * @方法描述: 向所有的用户发送消息
	 *
	 * @param message
	 */
	public static void sendMessageToAll(Map<String, Session> onlineSessions,String message) {
		onlineSessions.forEach((id, session) -> {
			try {
				session.getBasicRemote().sendText(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * @方法描述: 发送组消息
	 *
	 */
	public static void sendGroupMessage() {

	}
}
