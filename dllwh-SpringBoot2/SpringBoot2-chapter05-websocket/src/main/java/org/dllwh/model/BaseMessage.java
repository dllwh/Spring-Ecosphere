package org.dllwh.model;

import java.io.Serializable;

import lombok.Data;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: TODO(这里用一句话描述这个类的作用)
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2019-12-19 5:37:44 PM
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Data
public class BaseMessage implements Serializable {

	private static final long serialVersionUID = -830133137376403453L;
	/** 来源 */
	private String sender;
	/** 发送者名称 */
	private String senderName;
	/** 目标 */
	private String receiver;
	/** 内容 */
	private String content;
	/** 是否已读，1是0否 */
	private Integer ifRead;
	/** 是否删除，1是0否 */
	private Integer ifDelete;
	/** 是否撤回，1是0否 */
	private Integer ifBack;
	/** 消息创建时间 */
	private Long createTime;
	/** 消息类型，用户自定义消息类别(0:text、1:image、2:voice、3:video、4:music、5:news) */
	private Integer msgType;
	/** 附加内容 */
	private String extra;
}
