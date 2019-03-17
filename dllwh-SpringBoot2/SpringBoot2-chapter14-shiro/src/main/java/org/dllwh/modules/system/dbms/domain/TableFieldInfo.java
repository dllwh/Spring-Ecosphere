package org.dllwh.modules.system.dbms.domain;

import lombok.Data;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 列信息
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2019年1月10日 上午9:19:57
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Data
public class TableFieldInfo {
	/** 列名 */
	private String	field;
	/** 类型 */
	private String	type;
	/** 是否能为空 */
	private String	nullAble;
	/** 键 */
	private String	key;
	/** 默认值 */
	private String	defaultValue;
	/** 额外信息 */
	private String	extra;
	/** 备注信息 */
	private String	remark;
}