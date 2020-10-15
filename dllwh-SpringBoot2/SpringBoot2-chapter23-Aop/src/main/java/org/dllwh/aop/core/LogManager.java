package org.dllwh.aop.core;

import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 日志管理器
 * 
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2020-10-15 23:44:15
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
public class LogManager {
	/** 日志记录操作延时 */
	private final int OPERATE_DELAY_TIME = 10;
	/** 异步操作记录日志的线程池 */
	private ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(10);

	/** ----------------------------------------------------- Fields end */
	private LogManager() {
	}

	public static LogManager getInstance() {
		return logManager;
	}

	public static LogManager logManager = new LogManager();

	public void executeLog(TimerTask task) {
		executor.schedule(task, OPERATE_DELAY_TIME, TimeUnit.MILLISECONDS);
	}
}
