package org.dllwh.dao;

import java.util.List;
import java.util.Map;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 数据库接口
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019年5月25日 上午12:05:53
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
public interface GeneratorDao {
	int getTableCount(Map<String, Object> map);

	List<Map<String, Object>> getTableList(Map<String, Object> map);

	Map<String, String> getTableInfo(String tableName);

	List<Map<String, String>> getColumns(String tableName);
}