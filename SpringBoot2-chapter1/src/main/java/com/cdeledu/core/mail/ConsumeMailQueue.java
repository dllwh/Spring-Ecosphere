package com.cdeledu.core.mail;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 线程池消费者队列 beta
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年12月11日 下午4:11:54
 * @版本: V 0.1 beta
 * @since: JDK 1.7
 */
@Slf4j
@Component
public class ConsumeMailQueue {

	private static boolean	isRunning	= true;	// 发送邮件线程状态
	@Autowired
	MailQueue				mailQueue;

	@PostConstruct
	public void startThread() {
		ExecutorService executorService = Executors.newFixedThreadPool(2);// 两个大小的固定线程池
		executorService.submit(new Runnable() {

			@Override
			public void run() {
				log.debug("邮件发送消息线程启动");

				while (isRunning) {
					try {
						mailQueue.consume();
						Thread.sleep(1000); // 发送一个邮件休息1秒，防止发送过快被锁定
					} catch (Exception e) {
						log.error("邮件发送队列出错", e);
					}
				}
			}
		});
	}

	@PreDestroy
	public void stopThread() {
		isRunning = false;
		log.info("destroy");
	}
}
