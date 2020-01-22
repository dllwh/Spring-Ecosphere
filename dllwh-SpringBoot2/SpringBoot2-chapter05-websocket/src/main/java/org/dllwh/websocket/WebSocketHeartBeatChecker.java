package org.dllwh.websocket;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.dllwh.model.WebSocketInfo;
import org.dllwh.websocket.service.TodoAtRemovedService;
import org.dllwh.websocket.service.WebSocketManagerService;
import org.joda.time.DateTime;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 心跳监测
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2020-01-20
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Slf4j
@Configuration
public class WebSocketHeartBeatChecker {
	/**
	 * 
	 * @方法描述: 定时检测 WebSocket 的心跳时间跟现在的间隔，超过设定的值说明失去了心跳，就去除他，并更新数据库
	 * 
	 *        基于每次 WebSocket 的心跳都更新其心跳时间
	 *
	 * @param webSocketManager 要检测的容器
	 * @param timeSpan         检查到心跳更新时间大于这么毫秒就认为断开了（心跳时间）
	 * @param errorTolerant    容忍没有心跳次数
	 * @param todoAtRemoved    在删除的时候额外需要做的事情
	 */
	public void check(WebSocketManagerService webSocketManager, long timeSpan, int errorTolerant,
			TodoAtRemovedService todoAtRemoved) {
		log.error("WebSocket 心跳检测");
		final long timeSpans = timeSpan * errorTolerant;
		List<WebSocketInfo> toRemoves = new LinkedList<>();

		Map<String, WebSocketInfo> socketMap = webSocketManager.localWebSocketMap();
		if (socketMap != null) {
			socketMap.forEach((identifier, webSocket) -> {
				long interval = DateTime.now().getMillis() - webSocket.getLastHeart().getTime();
				if (interval >= timeSpans) {
					// 说明失去心跳了
					log.info("{} 失去心跳了", identifier);
					toRemoves.add(webSocket);
				}
			});
		}

		if (toRemoves != null && toRemoves.size() > 0) {
			for (WebSocketInfo webSocket : toRemoves) {
				// 内存删了
				socketMap.remove(webSocket.getIdentifier());
			}
			// 额外比如还有数据库操作
			todoAtRemoved.todoWith(toRemoves);
		}
		todoAtRemoved.todoWith(toRemoves);
	}
}
