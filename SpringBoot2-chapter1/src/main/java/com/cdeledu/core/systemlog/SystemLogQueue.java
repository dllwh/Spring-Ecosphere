package com.cdeledu.core.systemlog;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import com.cdeledu.model.LoggerEntity;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 日志队列
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年10月23日 下午9:22:15
 * @版本: V1.0
 * @since: JDK 1.7
 */
@Component
public class SystemLogQueue {
	private BlockingQueue<LoggerEntity> blockingQueue = new LinkedBlockingQueue<>();

	public void add(LoggerEntity systemLog) {
		blockingQueue.add(systemLog);
	}

	public LoggerEntity poll() throws InterruptedException {
		return blockingQueue.poll(1, TimeUnit.SECONDS);
	}
}
