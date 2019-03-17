package org.dllwh.core.configuration;

import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 实现不同任务使用不同的线程进行任务执行
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年10月10日 上午11:45:30
 * @版本: V1.0
 * @since: JDK 1.8
 */
@EnableAsync
@Configuration
public class ThreadPoolConfig {
	/**
	 * 配置线程池
	 * 
	 * @return
	 */
	@Bean(name = "scheduledPoolTaskExecutor")
	public ThreadPoolTaskExecutor getAsyncThreadPoolTaskExecutor() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		// 线程池创建时候初始化的线程数
		taskExecutor.setCorePoolSize(20);
		// 线程池最大的线程数
		taskExecutor.setMaxPoolSize(200);
		// 用来缓冲执行任务的队列
		taskExecutor.setQueueCapacity(25);
		// 允许线程的空闲时间
		taskExecutor.setKeepAliveSeconds(200);
		// 线程池名的前缀：设置好了之后可以方便我们定位处理任务所在的线程池
		taskExecutor.setThreadNamePrefix("oKong-Scheduled-");
		// 线程池对拒绝任务（无线程可用）的处理策略，目前只支持AbortPolicy、CallerRunsPolicy
		taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		// 调度器shutdown被调用时等待当前被调度的任务完成
		taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
		// 等待时长
		taskExecutor.setAwaitTerminationSeconds(60);
		taskExecutor.initialize();
		return taskExecutor;
	}
	
	@Bean(name = "taskScheduler")
	public TaskScheduler getThreadPoolTaskExecutor() {
		ThreadPoolTaskScheduler taskExecutor = new ThreadPoolTaskScheduler();
		taskExecutor.setPoolSize(20);
		// 线程池名的前缀：设置好了之后可以方便我们定位处理任务所在的线程池
		taskExecutor.setThreadNamePrefix("oKong-taskExecutor-");
		// 线程池对拒绝任务（无线程可用）的处理策略，目前只支持AbortPolicy、CallerRunsPolicy
		taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		// 调度器shutdown被调用时等待当前被调度的任务完成
		taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
		// 等待时长
		taskExecutor.setAwaitTerminationSeconds(60);
		taskExecutor.initialize();
		return taskExecutor;
	}
}