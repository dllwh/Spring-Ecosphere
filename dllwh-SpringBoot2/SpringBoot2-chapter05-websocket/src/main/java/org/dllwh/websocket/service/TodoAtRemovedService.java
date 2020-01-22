package org.dllwh.websocket.service;

import java.util.List;

import org.dllwh.model.WebSocketInfo;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: TODO(这里用一句话描述这个类的作用)
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2020-01-22
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
public interface TodoAtRemovedService {
	/**
	 * 
	 * @方法描述: 在删除的时候额外要干什么
	 *
	 * @param webSockets
	 */
	void todoWith(List<WebSocketInfo> webSockets);
}
