package com.cdeledu.core.task;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 监控任务
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年10月10日 下午3:04:53
 * @版本: V1.0
 * @since: JDK 1.8
 */
@Component
@Slf4j
public class MonitorTask {

	@Scheduled(fixedRate = 600000)
	public void getCurrentDate() {
		// 1监控CPU
		addXtMonitor();
		// 2监控内存
		addXtMonitorMEM();
		// 3监控主服务器
		addXtMonitorCPU();
		log.info("Scheduled定时任务执行：" + new Date());
	}

	/**
	 * @方法描述 : 主服务器监控
	 */
	private void addXtMonitor() {
	}

	/**
	 * @方法描述 : 内存监控
	 */
	private void addXtMonitorMEM() {
	}

	/**
	 * @方法描述 :CPU监控
	 */
	private void addXtMonitorCPU() {
	}
}
