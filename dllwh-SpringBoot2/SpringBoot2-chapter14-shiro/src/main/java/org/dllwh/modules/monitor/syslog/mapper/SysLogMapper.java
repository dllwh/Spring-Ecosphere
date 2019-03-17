package org.dllwh.modules.monitor.syslog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import org.dllwh.framework.model.LoggerEntity;
import org.dllwh.modules.monitor.syslog.domain.SysLoginLog;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述:
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年12月9日 下午7:13:05
 * @版本: V1.0.2
 * @since: JDK 1.8
 */
@Mapper
public interface SysLogMapper {
	/**
	 * 访问异常日志
	 */
	int insertExpLog(LoggerEntity expLog);

	List<LoggerEntity> getExpLogList(LoggerEntity expLog);

	int countExpLog(LoggerEntity expLog);

	/**
	 * 登录日志
	 */
	int insertLoginLog(SysLoginLog loginLog);

	List<SysLoginLog> getLoginLogList(SysLoginLog loginLog);

	List<SysLoginLog> getLoginLogByUserName(String userName);

	int countLoginLog(SysLoginLog loginLog);

	/**
	 * 操作日志
	 */
	int insertOperateLog(LoggerEntity operateLog);

	List<LoggerEntity> getOperateLogList(LoggerEntity expLog);

	int countOperateLog(LoggerEntity expLog);
}
