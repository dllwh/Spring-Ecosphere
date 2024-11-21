package org.dllwh.handler;

import java.util.List;

import org.dllwh.model.UserInfo;

import com.google.common.collect.Lists;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: websocket消息处理器类
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2020-01-20
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
public class ChatMessageHelper {
	/**
	 * 
	 * @方法描述: 广播
	 *
	 * @param message
	 */
	public static void broadcast(String message) {

	}

	/**
	 * 
	 * @方法描述: 发送文字信息
	 *
	 * @param message
	 */
	public static void send(String message) {

	}

	/**
	 * 
	 * @方法描述: 获取所有在线用户列表
	 *
	 * @param userid
	 * @return
	 */
	public static List<UserInfo> getAllOnlineUsers(long userid) {
		List<UserInfo> onlineUserList = Lists.newArrayList();
		return onlineUserList;
	}

	/**
	 * 
	 * @方法描述: 刷新在线用户列表
	 *
	 */
	public static void sendRefreshUserList() {

	}

	/**
	 * 
	 * @方法描述: 发送离线消息
	 *
	 */
	public static void sendOfflineMessage() {

	}

	/**
	 * @方法描述: 发送用户状态
	 *
	 */
	public static void sendUserStatus() {

	}

	/**
	 * @方法描述: 发送短信信息
	 *
	 */
	public static void sendSmsMsg() {

	}

	/**
	 * @方法描述: 将来自短信的信息发送给在线用户
	 *
	 */
	public static void sendSms2Message() {

	}
}
