package org.dllwh.modules.monitor.syslog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.dllwh.framework.model.LoggerEntity;
import org.dllwh.modules.monitor.syslog.domain.SysLoginLog;
import org.dllwh.modules.monitor.syslog.mapper.SysLogMapper;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 系统日志：
 * 
 *       <pre>
 * 目前存储MySQL中，后期会存储在MongoDB中
 *       </pre>
 * 
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年12月9日 下午7:12:32
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@Service("sysLogService")
public class SysLogServiceImpl implements SysLogService {
	@Autowired
	private SysLogMapper sysLogMapper;

	@Override
	public int insertExpLog(LoggerEntity expLog) {
		return sysLogMapper.insertExpLog(expLog);
	}

	@Override
	public List<LoggerEntity> getExpLogList(LoggerEntity expLog) {
		return sysLogMapper.getExpLogList(expLog);
	}

	@Override
	public int countExpLog(LoggerEntity expLog) {
		return sysLogMapper.countExpLog(expLog);
	}

	@Override
	public int insertLoginLog(SysLoginLog loginLog) {
		return sysLogMapper.insertLoginLog(loginLog);
	}

	@Override
	public List<SysLoginLog> getLoginLogList(SysLoginLog loginLog) {
		return sysLogMapper.getLoginLogList(loginLog);
	}

	@Override
	public List<SysLoginLog> getLoginLogByUserName(String userName) {
		return null;
	}

	@Override
	public int countLoginLog(SysLoginLog loginLog) {
		return sysLogMapper.countLoginLog(loginLog);
	}

	@Override
	public int insertOperateLog(LoggerEntity operateLog) {
		return sysLogMapper.insertExpLog(operateLog);
	}

	@Override
	public List<LoggerEntity> getOperateLogList(LoggerEntity operateLog) {
		return sysLogMapper.getOperateLogList(operateLog);
	}

	@Override
	public int countOperateLog(LoggerEntity operateLog) {
		return sysLogMapper.countOperateLog(operateLog);
	}
}