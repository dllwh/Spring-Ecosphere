package org.dllwh.model;

import javax.websocket.Session;

import lombok.Data;

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
@Data
public class SocketUser {
	private Session session;
	private int userId;
}
