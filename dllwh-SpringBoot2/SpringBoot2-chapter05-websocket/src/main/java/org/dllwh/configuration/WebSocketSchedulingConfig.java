package org.dllwh.configuration;

import org.dllwh.websocket.WebSocketHeartBeatChecker;
import org.dllwh.websocket.service.WebSocketManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 开启心跳监测
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2020-01-22
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Configuration
@EnableScheduling
@ConditionalOnProperty(prefix = "webSocket.heartCheck", name = "enabled", havingValue = "true")
public class WebSocketSchedulingConfig {
	@Value(value = "${webSocket.heartCheck.timeSpan}")
	private long timeSpan;
	@Value(value = "${webSocket.heartCheck.errorToleration}")
	private int errorToleration;

	@Autowired
	private WebSocketManagerService webSocketManagerService;
	@Autowired
	private WebSocketHeartBeatChecker webSocketHeartBeatChecker;

	@Bean
	public TaskScheduler taskScheduler() {
		ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
		return scheduler;
	}

	/**
	 * @方法描述: 定时检测 WebSocket 的心跳
	 *
	 */
	@Scheduled(cron = "${webSocket.heartCheck.trigger}")
	public void webSocketHeartCheckJob() {
		webSocketHeartBeatChecker.check(webSocketManagerService, timeSpan, errorToleration, (webSocket) -> {
			// 数据库操作...
		});
	}
}
