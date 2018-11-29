package com.cdeledu.core.systemlog;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdeledu.model.LoggerEntity;

import lombok.extern.slf4j.Slf4j;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 日志存储队列消费者
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年10月23日 下午9:23:54
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@Component
@Slf4j
public class SystemLogConsumer implements Runnable {

	private boolean active = true;
	private Thread thread;
	@Autowired
	private SystemLogQueue auditLogQueue;

	@PostConstruct
	public void init() {
		thread = new Thread(this);
		thread.start();
	}

	@PreDestroy
	public void close() {
		active = false;
	}

	@Override
	public void run() {
		while (active) {
			execute();
		}
	}

	public void execute() {
		try {
			LoggerEntity systemLog = auditLogQueue.poll();
			if (systemLog != null) {
				log.info("------------------------日志存储" + systemLog.toString());
			}
		} catch (InterruptedException e) {
			log.error("日志存储出现异常，异常信息:{}", e.getMessage());
		}
	}
}
