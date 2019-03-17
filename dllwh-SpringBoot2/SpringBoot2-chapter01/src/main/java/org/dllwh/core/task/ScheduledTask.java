package org.dllwh.core.task;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ScheduledTask {
	/**
	 * 自动扫描，启动时间点之后60秒执行一次
	 */
	@Scheduled(fixedRate = 60000)
	public void getCurrentDate() {
		log.info("Scheduled定时任务执行：" + new Date());
	}
}