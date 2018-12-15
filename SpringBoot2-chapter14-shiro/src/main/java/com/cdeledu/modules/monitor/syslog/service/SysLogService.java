package com.cdeledu.modules.monitor.syslog.service;

import java.util.List;

import com.cdeledu.framework.model.LoggerEntity;

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
	int insertLoginLog(LoggerEntity expLog);
	
	List<LoggerEntity> getLoginLogList(LoggerEntity expLog);
	
	List<LoggerEntity> getLoginLogByUserID(int userId);
	
	int countLoginLog(LoggerEntity expLog);
	
	/**
	 * 操作日志
	 */
	int insertOperateLog(LoggerEntity operateLog);
	
	List<LoggerEntity> getOperateLogList(LoggerEntity expLog);
	
	int countOperateLog(LoggerEntity expLog);
}
