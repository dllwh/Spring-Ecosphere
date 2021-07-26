package org.dllwh.task.config;

import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 定时任务配置，配置线程池，使用不同线程执行任务，提升效率
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @版本: V1.0
 * @since: JDK 1.8
 */
@Configuration
public class TaskSchedulerConfig {
	/**
	 * @方法描述: ThreadPoolTaskScheduler 任务调度器
	 */
	@Bean(name = "taskScheduler")
	public TaskScheduler getThreadPoolTaskExecutor() {
		ThreadPoolTaskScheduler taskExecutor = new ThreadPoolTaskScheduler();
		// 线程池对拒绝任务（无线程可用）的处理策略，目前只支持AbortPolicy、CallerRunsPolicy
		taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		taskExecutor.initialize();
		return taskExecutor;
	}
}
