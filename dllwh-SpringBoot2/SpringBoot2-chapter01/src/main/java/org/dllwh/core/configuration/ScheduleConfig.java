package org.dllwh.core.configuration;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

@Configuration
public class ScheduleConfig implements SchedulingConfigurer {
	final static Logger	logger	= LoggerFactory.getLogger(ScheduleConfig.class);
	@Autowired
	TaskScheduler		taskScheduler;
						
	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.setTaskScheduler(taskScheduler);
		taskRegistrar.getScheduler().schedule(new Runnable() {
			@Override
			public void run() {
				logger.info("SchedulingConfigurer定时任务：" + new Date());
			}
		}, new CronTrigger("0 0/10 * * * ? "));// 每10分钟执行一次
	}
}