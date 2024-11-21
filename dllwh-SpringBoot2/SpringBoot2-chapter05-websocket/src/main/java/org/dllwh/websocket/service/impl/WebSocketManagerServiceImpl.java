package org.dllwh.websocket.service.impl;

import java.util.Map;

import org.dllwh.model.WebSocketInfo;
import org.dllwh.websocket.service.WebSocketManagerService;
import org.springframework.stereotype.Service;

/**
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
@Service("WebSocketManagerService")
public class WebSocketManagerServiceImpl implements WebSocketManagerService {

	@Override
	public WebSocketInfo get(String identifier) {
		return null;
	}

	@Override
	public void put(String identifier, WebSocketInfo webSocket) {

	}

	@Override
	public void remove(String identifier) {

	}

	@Override
	public Map<String, WebSocketInfo> localWebSocketMap() {
		return null;
	}
}
