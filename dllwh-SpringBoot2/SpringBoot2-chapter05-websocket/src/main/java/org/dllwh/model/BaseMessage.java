package org.dllwh.model;

import java.io.Serializable;

import lombok.Data;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 自定义消息对象
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2019-12-19 5:37:44 PM
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Data
public class BaseMessage implements Serializable {

	private static final long serialVersionUID = -830133137376403453L;
	/** 消息id */
	private long msgId;
	/** 消息发送者账号 */
	private String sender;
	/** 消息发送者接收者 */
	private String receiver;
	/** 是否已读，1是0否 */
	private Integer ifRead;
	/** 是否删除，1是0否 */
	private Integer ifDelete;
	/** 是否撤回，1是0否 */
	private Integer ifBack;
	/** 消息发送时间 */
	private Long createTime;
	/** content 内容格式 */
	private String format;
	/**
	 * 消息类型，用户自定义消息类别(文本:text、图片:image、语音:voice、视频:video;、 文件:file)
	 */
	private String msgType;
	/** 附加内容 */
	private String extra;
}
