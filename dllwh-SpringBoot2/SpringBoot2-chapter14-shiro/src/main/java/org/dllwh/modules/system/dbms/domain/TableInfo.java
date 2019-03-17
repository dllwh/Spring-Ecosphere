package org.dllwh.modules.system.dbms.domain;

import java.util.List;

import lombok.Data;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 表信息
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2019年1月10日 上午9:22:08
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Data
public class TableInfo {
	/** 表名 */
	private String					tableName;
	/** 备注信息 */
	private String					tableRemark;
	/** 列 */
	private List<TableFieldInfo>	fields;
	/** 索引信息 */
	private List<TableKeyInfo>		keys;
}
