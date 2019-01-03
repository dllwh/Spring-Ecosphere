package com.cdeledu.modules.system.domain;

import java.util.List;

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
 * @类描述: 菜单表实体类
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年12月12日 下午11:55:19
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "菜单表实体类")
public class SysMenu  extends BaseEntity{
	/** 菜单ID */
	private String		id;
	/** 菜单名称 */
	private String		menuName;
	/** 菜单链接地址 */
	private String		menuUrl;
	/** 排序 */
	private Integer		sequence;
	/** 类型。1:目录，默认值；2：菜单；3：按钮 */
	private Integer		type;
	/** 父菜单编号 */
	private Integer		parentCode;
	/** 是否可见(启用/禁用),不为空 */
	private Integer		ifVisible;
	/** 是否有效;-1:删除;0:不可用,默认值;1:可用 */
	private String		ifEnabled;
	/** 父级菜单名称 */
	private String		parentName;
	/** 菜单图标样式 */
	private String		iconClass;
	/** tree属性 */
	private Boolean		open;
	private List<?>		childrenList;
	
	/** 权限字符串,menu例子：role:*，button例子：role:create */
	private String		permission;
}