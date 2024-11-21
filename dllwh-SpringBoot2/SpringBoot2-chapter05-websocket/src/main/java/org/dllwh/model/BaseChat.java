package org.dllwh.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: TODO(这里用一句话描述这个类的作用)
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2020-01-03 12:05:37 AM
 * @版本: V 1.0.1
 * @since: JDK 1.8
 * @see <a href="">TODO(连接内容简介)</a>
 */
@Data
public class BaseChat implements Serializable {

	private static final long serialVersionUID = 9073969839191219105L;

	/** 消息类型，用户自定义消息类别 */
	private long id;
	/** 消息类型，用户自定义消息类别 */
	private String action;
	private String chatContent;
	private String fromUserId;
	private String toUserId;
	private Byte chatType;
	private Byte chatState;
	private Date chatCreateTime;
}
