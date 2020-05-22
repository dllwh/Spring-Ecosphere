package org.dllwh.task.config;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 实现SchedulingConfigurer并重写configureTasks方法
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2020-05-18
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Component
@Slf4j
public class TaskConfig implements SchedulingConfigurer {
	/** 注入线程池 */
	@Autowired
	private TaskScheduler taskScheduler;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		// 添加定时任务到线程池
		taskRegistrar.setTaskScheduler(taskScheduler);

		taskRegistrar.getScheduler().schedule(new Runnable() {

			@Override
			public void run() {
				// log.info("SchedulingConfigurer定时任务：" + LocalDateTime.now().format(formatter));
			}
		}, new CronTrigger("0/5 * * * * * "));// 每5秒执行一次
	}
}
