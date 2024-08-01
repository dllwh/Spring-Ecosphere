package org.dllwh.model;

import java.io.Serializable;

import lombok.Data;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 聊天消息
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2020-01-20
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Data
public class IMMessage implements Serializable {
	private static final long serialVersionUID = 215190865043579911L;
	/** 命令类型[login]或者[system]或者[logout] */
	private String cmd;
	/** 命令发送时间 */
	private long time;
	/** 发送人 */
	private String sender;
	/** 接收人 */
	private String receiver;
	/** 消息内容 */
	private String content;
}
