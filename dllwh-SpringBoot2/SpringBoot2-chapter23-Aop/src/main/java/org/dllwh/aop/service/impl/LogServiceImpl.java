package org.dllwh.aop.service.impl;

import java.util.List;

import org.dllwh.aop.dao.SysOperateLogDao;
import org.dllwh.aop.model.WebLog;
import org.dllwh.aop.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: TODO(这里用一句话描述这个类的作用)
 * 
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2020-10-18 03:23:08
 * @版本: V 1.0.1
 * @since: JDK 1.8
 *
 * @see <a href="">TODO(连接内容简介)</a>
 */
@Service
public class LogServiceImpl implements LogService {

	@Autowired
	private SysOperateLogDao sysOperateLogDao;

	/**
	 * TODO(这里用一句话描述这个方法的作用)
	 *
	 * @param record
	 * @return
	 */
	@Override
	public int insert(WebLog record) {
		return sysOperateLogDao.insert(record);
	}

	/**
	 * TODO(这里用一句话描述这个方法的作用)
	 *
	 * @param id
	 * @return
	 */
	@Override
	public WebLog getWebLog(Integer id) {
		return sysOperateLogDao.getWebLog(id);
	}

	/**
	 * TODO(这里用一句话描述这个方法的作用)
	 *
	 * @param record
	 * @return
	 */
	@Override
	public List<WebLog> getWebLogList(WebLog record) {
		return sysOperateLogDao.getWebLogList(record);
	}

}
