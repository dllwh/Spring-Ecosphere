package org.dllwh.webSocket.chat;

import com.alibaba.fastjson.JSON;

import lombok.Getter;
import lombok.Setter;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 聊天消息类
 * @创建者: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2019-12-08 1:09:49 AM
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Getter
@Setter
public final class Message {
	public static final String ENTER = "ENTER";
	public static final String SPEAK = "SPEAK";
	public static final String QUIT = "QUIT";
	/** 消息类型 */
	private String type;
	/** 发送人 */
	private String username;
	/** 发送消息 */
	private String msg;
	/** 在线用户数 */
	private int onlineCount;

	public static String jsonStr(String type, String username, String msg, int onlineTotal) {
		return JSON.toJSONString(new Message(type, username, msg, onlineTotal));
	}

	public Message(String type, String username, String msg, int onlineCount) {
		this.type = type;
		this.username = username;
		this.msg = msg;
		this.onlineCount = onlineCount;
	}
}
