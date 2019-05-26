package org.dllwh.service;

import java.util.List;
import java.util.Map;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: TODO(这里用一句话描述这个类的作用)
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019年5月25日 上午12:11:56
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
public interface SysGeneratorService {
	int getTableCount(Map<String, Object> map);

	List<Map<String, Object>> getTableList(Map<String, Object> map);

	Map<String, String> getTableInfo(String tableName);

	List<Map<String, String>> getColumns(String tableName);
}