package org.dllwh.modules.monitor.syslog.service;

import java.util.List;

import org.dllwh.framework.model.LoggerEntity;
import org.dllwh.modules.monitor.syslog.domain.SysLoginLog;

public interface SysLogService {
	/**
	 * 访问异常日志
	 */
	public int insertExpLog(LoggerEntity expLog);
	
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
