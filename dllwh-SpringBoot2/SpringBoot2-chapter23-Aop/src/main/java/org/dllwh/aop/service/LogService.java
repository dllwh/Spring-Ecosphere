package org.dllwh.aop.service;

import java.util.List;

import org.dllwh.aop.model.WebLog;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 操作日志服务
 * 
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2020-10-18 03:21:29
 * @版本: V 1.0.1
 * @since: JDK 1.8
 *
 */
public interface LogService {
	int insert(WebLog record);

	WebLog getWebLog(Integer id);

	List<WebLog> getWebLogList(WebLog record);
}
