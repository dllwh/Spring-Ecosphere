package com.cdeledu.modules.monitor.syslog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdeledu.framework.model.LoggerEntity;
import com.cdeledu.modules.monitor.syslog.mapper.SysLogMapper;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: TODO(这里用一句话描述这个类的作用)
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
	public int insertLoginLog(LoggerEntity loginLog) {
		return sysLogMapper.insertLoginLog(loginLog);
	}
	
	@Override
	public List<LoggerEntity> getLoginLogList(LoggerEntity loginLog) {
		return sysLogMapper.getLoginLogList(loginLog);
	}
	

	@Override
	public List<LoggerEntity> getLoginLogByUserID(int userId) {
		return null;
	}
	
	@Override
	public int countLoginLog(LoggerEntity loginLog) {
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