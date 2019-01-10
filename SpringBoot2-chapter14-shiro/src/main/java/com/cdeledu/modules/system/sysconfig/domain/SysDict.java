package com.cdeledu.modules.system.sysconfig.domain;

import com.cdeledu.common.BaseEntity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 数据字典
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2019年1月8日 下午4:12:39
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel("数据字典")
public class SysDict extends BaseEntity {
	/** 父ID */
	private Integer	parentId;
	/** 字典名称 */
	private String	itemName;
	/** 字典值 */
	private String	itemCode;
	/** 字典排序 */
	private Long	sequence;
	/** 字典标签 */
	private String	dictLabel;
	/** 是否有效;-1:删除;0:不可用,默认值;1:可用 */
	private Integer	ifEnabled;
}