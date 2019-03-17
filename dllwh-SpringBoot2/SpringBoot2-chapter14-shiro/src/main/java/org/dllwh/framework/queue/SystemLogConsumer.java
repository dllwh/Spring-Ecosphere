package org.dllwh.framework.queue;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.dllwh.framework.model.LoggerEntity;

import lombok.extern.slf4j.Slf4j;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 志存储队列消费者
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年12月14日 下午9:58:40
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@Component
@Slf4j
public class SystemLogConsumer implements Runnable {
	
	private boolean			active	= true;
	private Thread			thread;
	@Autowired
	private SystemLogQueue	auditLogQueue;
							
	/**
	 * @方法描述:容器加载时执行init（）方法
	 */
	@PostConstruct
	public void init() {
		thread = new Thread(this);// 实例化
		thread.start(); // 启动线程，启动后执行 run（）方法
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
		// 判断缓冲队列是否存在记录
		try {
			LoggerEntity systemLog = auditLogQueue.poll(); // 从队列中提取信息
			if (systemLog != null) { // 判断消息是否为空
				// 如果不为空则执行相应操作
				log.info("------------------------日志存储" + systemLog.toString());
			} else {
				// 如果为空则让线程进入等待状态
			}
		} catch (InterruptedException e) {
			log.error("日志存储出现异常，异常信息:{}", e.getMessage());
		}
	}
}