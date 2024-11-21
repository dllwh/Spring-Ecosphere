package org.dllwh.common;

import lombok.Getter;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 系统常量
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2020-01-13
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
public class ChatEnum {

	/** 0:未知 */
	public final static String CHATTYPE_UNDEFINED = "undefined";
	/** 1:公聊 */
	public final static String CHATTYPE_GROUP = "group";
	/** 2:私聊 */
	public final static String CHATTYPE_USER = "user";
	/** 在线用户数 */
	public static String ONLINE_COUNT = "onLineCount";
	/** 在线用户列表 */
	public static String ONLINE_LIST = "onLineList";

	/** 请求类型：消息 */
	public static final String REQUEST_MESSAGE = "msg";
	/** 请求类型：登录 */
	public static final String REQUEST_LOGIN = "login";
	/** 请求类型：退出 */
	public static final String REQUEST_EXIT = "exit";
	/** 请求类型：文件传输 */
	public static final String REQUEST_FILE = "file";
	/** 请求类型：语音 */
	public static final String REQUEST_VOICE = "voice";
	/** 请求类型：视频 */
	public static final String REQUEST_VIDEO = "video";

	/**
	 * @类描述: 消息类型
	 */
	@Getter
	public enum MessageType {
		/** 文本消息 */
		TEXT("text", "文本消息"),
		/** 图片消息 */
		IMAGE("image", "图片消息"),
		/** 语音消息 */
		VOICE("voice", "语音消息"),
		/** 视频消息 */
		VIDEO("video", "视频消息"),
		/** 文件消息 */
		FILE("file", "文件消息"),;
		private String code;
		private String remark;

		private MessageType(String code, String remark) {
			this.code = code;
			this.remark = remark;
		}
	}

	/**
	 * @类描述: 发送的目标类型
	 */
	@Getter
	public enum TargetType {
		/** 给用户发消息 */
		USERS("users", "给用户发消息"),
		/** 给群发消息 */
		CHATGROUPS("chatgroups", "给群发消息"),
		/** 给聊天室发消息 */
		CHATROOMS("chatrooms", "给聊天室发消息");
		private String code;
		private String remark;

		private TargetType(String code, String remark) {
			this.code = code;
			this.remark = remark;
		}
	}

	/**
	 * @类描述: 用来判断单聊还是群聊
	 */
	@Getter
	public enum ChatType {
		/** 单聊 */
		chat("chat", "单聊"),
		/** 群聊 */
		GROUPCHAT("groupchat", "群聊");
		private String code;
		private String remark;

		private ChatType(String code, String remark) {
			this.code = code;
			this.remark = remark;
		}
	}

	@Getter
	public enum UserState {
		/** 在线 */
		Online("online", "在线"),
		/** 离线 */
		Offline("offline", "离线"),
		/** 隐身 */
		Stealth("stealth", "隐身"),
		/** 离开 */
		Leave("leave", "离开");

		private String code;
		private String remark;

		private UserState(String code, String remark) {
			this.code = code;
			this.remark = remark;
		}
	}
}
