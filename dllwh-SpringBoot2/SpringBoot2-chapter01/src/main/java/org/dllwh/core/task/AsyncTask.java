package org.dllwh.core.task;

import java.util.Random;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AsyncTask {
	public static Random random = new Random();

	@Async("scheduledPoolTaskExecutor")
	public void doTaskOne() throws Exception {
		log.info("开始做任务一");
		long start = System.currentTimeMillis();
		Thread.sleep(random.nextInt(10000));
		long end = System.currentTimeMillis();
		log.info("完成任务一，耗时：" + (end - start) + "毫秒");
	}

	@Async("scheduledPoolTaskExecutor")
	public void doTaskTwo() throws Exception {
		log.info("开始做任务二");
		long start = System.currentTimeMillis();
		Thread.sleep(random.nextInt(10000));
		long end = System.currentTimeMillis();
		log.info("完成任务二，耗时：" + (end - start) + "毫秒");
	}

	@Async("scheduledPoolTaskExecutor")
	public void doTaskThree() throws Exception {
		log.info("开始做任务三");
		long start = System.currentTimeMillis();
		Thread.sleep(random.nextInt(10000));
		long end = System.currentTimeMillis();
		log.info("完成任务三，耗时：" + (end - start) + "毫秒");
	}
}