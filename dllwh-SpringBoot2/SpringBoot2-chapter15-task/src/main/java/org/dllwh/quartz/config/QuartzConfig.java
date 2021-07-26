package org.dllwh.quartz.config;

import org.dllwh.quartz.job.QuartzTask;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: TODO(这里用一句话描述这个类的作用)
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2020-05-20
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Configuration
public class QuartzConfig {
	/**
	 * 定义**JobDetail**，JobDetail由JobBuilder构建，同时关联了任务
	 */
	@Bean
	public JobDetail testQuartzDetail() {
		// 关联了任务QuartzTask
		return JobBuilder.newJob(QuartzTask.class).storeDurably().build();
	}

	/**
	 * 定义定时调度**Trigger** 1. 简单实现类SimpleScheduleBuilder用于构建Scheduler 2.
	 * TriggerBuilder则用于构建Trigger
	 */
	@Bean
	public Trigger testQuartzTrigger() {
		SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule();
		// 设置时间周期:每10秒执行一次，单位秒
		scheduleBuilder.withIntervalInSeconds(10);
		// 永久重复，一直执行下去
		scheduleBuilder.repeatForever();
		return TriggerBuilder.newTrigger().forJob(testQuartzDetail()).withSchedule(scheduleBuilder).build();
	}
}