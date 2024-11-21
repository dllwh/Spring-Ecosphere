package org.dllwh.model;

import java.util.Date;

import javax.websocket.Session;

import lombok.Data;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: WebSocket 信息
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2020-01-22
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Data
public class WebSocketInfo {
	/** 代表一个连接 */
	private Session session;
	/** 唯一标识 */
	private String identifier;
	/** 最后心跳时间 */
	private Date lastHeart;
}
