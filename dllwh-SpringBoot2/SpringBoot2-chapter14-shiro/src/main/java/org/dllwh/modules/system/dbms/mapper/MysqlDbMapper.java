package org.dllwh.modules.system.dbms.mapper;

import java.util.List;

import org.dllwh.modules.system.dbms.domain.TableInfo;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 数据库信息数据层
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2019年1月10日 上午9:50:15
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
public interface MysqlDbMapper {
	/**
	 * @方法描述 : 获取数据库名称
	 * @return
	 */
	String databaseName();

	/**
	 * @方法描述 : 获取表的信息
	 * @return
	 */
	List<TableInfo> tableInfoList();
}