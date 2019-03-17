package org.dllwh.framework.scheduled;

import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 调度中心
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年12月14日 下午10:57:10
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
public class ScheduledManager {
	// 异步操作记录日志的线程池
	private ScheduledThreadPoolExecutor	executor			= new ScheduledThreadPoolExecutor(10);
	private static ScheduledManager		scheduledManager	= new ScheduledManager();
															
	private ScheduledManager() {
	}
	
	public static ScheduledManager getInstance() {
		return scheduledManager;
	}
	
	public void executeLog(TimerTask task) {
		executor.schedule(task, 10, TimeUnit.MILLISECONDS);
	}
}