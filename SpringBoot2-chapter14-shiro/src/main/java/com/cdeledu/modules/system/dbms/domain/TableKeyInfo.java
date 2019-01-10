package com.cdeledu.modules.system.dbms.domain;

import java.util.List;

import lombok.Data;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 表的索引信息
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2019年1月10日 上午9:37:17
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Data
public class TableKeyInfo {
	/** 索引名称 */
	private String			name;
	/** 包含那些字段 */
	private List<String>	columns;
	/** 是否唯一 */
	private Boolean			unique;
	/** 索引类型 */
	private String			indexType;
	/** 索引注释 */
	private String			indexComment;
}