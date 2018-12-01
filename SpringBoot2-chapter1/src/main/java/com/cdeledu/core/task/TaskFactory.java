package com.cdeledu.core.task;

import java.util.TimerTask;

import com.cdeledu.model.LoggerEntity;

import lombok.extern.slf4j.Slf4j;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 任务工厂
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年12月1日 下午5:09:58
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@Slf4j
public class TaskFactory {
	/**
	 * @方法描述:操作日志
	 * @param loggerEntity
	 * @return
	 */
	public static TimerTask operateLog(LoggerEntity loggerEntity) {
		return new TimerTask() {
			@Override
			public void run() {
				try {
					log.info("-----操作日志存储----");
				} catch (Exception e) {
					log.error("保存日常操作日志异常!", e);
				}
			}
		};
	}
}