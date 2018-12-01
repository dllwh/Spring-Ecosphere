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
 * 		
 *       <pre>
 * 阻塞队列 (BlockingQueue)是Java util.concurrent包下重要的数据结构，BlockingQueue提供了线程安全的队列访问方式：
 * 当阻塞队列进行插入数据时，如果队列已满，线程将会阻塞等待直到队列非满；从阻塞队列取数据时，如果队列已空，线程将会阻塞等待直到队列非空。
 * 
 * LinkedBlockingQueue ：由链表结构组成的有界阻塞队列
 *       </pre>
 * 
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年10月23日 下午9:22:15
 * @版本: V1.0
 * @since: JDK 1.7
 */
@Component
public class SystemLogQueue {
	// 存储客户端（用户）提交的设置控制命令
	public static BlockingQueue<LoggerEntity> blockingQueue = new LinkedBlockingQueue<LoggerEntity>();
	
	/**
	 * @方法描述:把对应的消息 加入到队列中
	 * @param systemLog
	 */
	public void produce(LoggerEntity systemLog) {
		if (systemLog != null) {
			blockingQueue.add(systemLog);
		}
	}
	
	/**
	 * @方法描述:检查(调度线程池)，每秒执行一次，查看缓冲队列是否有 记录
	 * @return
	 * @throws InterruptedException
	 */
	public LoggerEntity poll() throws InterruptedException {
		return blockingQueue.poll(1, TimeUnit.SECONDS);
	}
}