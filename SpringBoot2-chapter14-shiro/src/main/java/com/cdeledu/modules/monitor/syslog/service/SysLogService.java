package com.cdeledu.modules.monitor.syslog.service;

import java.util.List;

import com.cdeledu.framework.model.LoggerEntity;
import com.cdeledu.modules.monitor.syslog.domain.SysLoginLog;

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
	int insertLoginLog(SysLoginLog expLog);
	
	List<SysLoginLog> getLoginLogList(SysLoginLog expLog);
	
	List<SysLoginLog> getLoginLogByUserName(String userName);
	
	int countLoginLog(SysLoginLog expLog);
	
	/**
	 * 操作日志
	 */
	int insertOperateLog(LoggerEntity operateLog);
	
	List<LoggerEntity> getOperateLogList(LoggerEntity expLog);
	
	int countOperateLog(LoggerEntity expLog);
}
