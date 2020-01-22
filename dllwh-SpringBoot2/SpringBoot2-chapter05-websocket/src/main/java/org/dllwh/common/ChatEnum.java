package org.dllwh.configuration;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: TODO(这里用一句话描述这个类的作用)
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2020-01-13
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
public final class ChatEnum {
	/** 0:text(文本消息) */
	public final static String TEXT = "text";
	/** 1:image(图片消息) */
	public final static String IMAGE = "image";
	/** 2:voice(语音消息) */
	public final static String VOICE = "voice";
	/** 3:video(视频消息) */
	public final static String VIDEO = "video";
	/** 4:link(连接消息) */
	public final static String LINK = "link";
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
}
