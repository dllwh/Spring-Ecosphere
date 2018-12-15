package com.cdeledu.framework.factory;

import java.util.TimerTask;

import com.cdeledu.common.util.SpringContextHelper;
import com.cdeledu.framework.model.LoggerEntity;
import com.cdeledu.modules.monitor.syslog.service.SysLogService;

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
	
	private static SysLogService sysLogService  = SpringContextHelper.getBeans("sysLogService");
	/**
	 * @方法描述:操作日志
	 * @param loggerEntity
	 * @return
	 */
	public  static TimerTask exceptionLog(LoggerEntity loggerEntity) {
		return new TimerTask() {
			@Override
			public void run() {
				try {
					log.warn("----------------------{}********",loggerEntity.getSessionId());
					sysLogService.insertExpLog(loggerEntity);
					// log.info("-----存储--异常--");
				} catch (Exception e) {
					log.error("保存日常操作日志异常!", e);
				}
			}
		};
	}
}