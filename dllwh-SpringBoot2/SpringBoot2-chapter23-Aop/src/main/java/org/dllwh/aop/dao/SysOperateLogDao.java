package org.dllwh.aop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.dllwh.aop.model.WebLog;
import org.springframework.stereotype.Repository;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: TODO(这里用一句话描述这个类的作用)
 * 
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2020-10-18 02:59:14
 * @版本: V 1.0.1
 * @since: JDK 1.8
 *
 */
@Repository
@Mapper
public interface SysOperateLogDao {
	int insert(WebLog record);

	WebLog getWebLog(Integer id);

	List<WebLog> getWebLogList(WebLog record);
}
