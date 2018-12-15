package com.cdeledu.framework.factory;

import com.cdeledu.common.util.SpringContextHelper;
import com.cdeledu.modules.monitor.syslog.service.SysLogService;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 常量的生产工厂
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年12月15日 上午12:16:28
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
public class ConstantFactory {
	/** 操作日志处理层 */
	public static SysLogService sysLogService = SpringContextHelper.getBeans("sysLogService");
}
