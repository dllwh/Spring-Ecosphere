package com.cdeledu.core.mail;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 邮件队列 beta
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年12月11日 下午4:07:19
 * @版本: V 0.1 beta
 * @since: JDK 1.7
 */
@Slf4j
@Component
public class MailQueue {
	static final int		QUEUE_MAX_SIZE	= 1000;										// 队列大小
	static BlockingQueue<?>	blockingQueue	= new LinkedBlockingQueue(QUEUE_MAX_SIZE);	// 缓存发送邮件的队列

	/**
	 * @方法描述 : 生成者入列
	 */
	public void produce() throws InterruptedException {
		log.info("生成者入列");
		blockingQueue.put(null);
	}

	/**
	 * @方法描述 : 消费者出列
	 */
	public void consume() throws InterruptedException {
		log.info("消费者出列");
		blockingQueue.poll(); // 移除并返回队列头部的元素，如果队列为空，则返回null
	}

	/**
	 * @方法描述 : 获取队列大小
	 */
	public int size() {
		return blockingQueue.size();
	}
}
