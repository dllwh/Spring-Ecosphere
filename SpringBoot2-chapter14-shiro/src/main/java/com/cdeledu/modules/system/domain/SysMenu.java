package com.cdeledu.modules.system.domain;

import java.security.Timestamp;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
@ApiModel(value = "菜单表实体类")
public class SysMenu {
	@ApiModelProperty(value = "菜单ID", name = "id", example = "1")
	private String		id;
	@ApiModelProperty(value = "菜单名称", name = "menuName")
	private String		menuName;
	@ApiModelProperty(value = " 菜单链接地址", name = "menuUrl")
	private String		menuUrl;
	@ApiModelProperty(value = "类型。1:目录，默认值；2：菜单；3：按钮；", name = "type", example = "1")
	private Integer		type;
	@ApiModelProperty(value = "父菜单编号", name = "parentCode", example = "1")
	private Integer		parentCode;
	@ApiModelProperty(value = "是否可见(启用/禁用),不为空", name = "ifVisible")
	private Integer		ifVisible;
	@ApiModelProperty(value = "是否有效;-1:删除;0:不可用,默认值;1:可用", name = "ifEnabled")
	private String		ifEnabled;
	@ApiModelProperty(value = "父级菜单名称", name = "parentName")
	private String		parentName;
	@ApiModelProperty(value = "菜单图标样式", name = "iconClass")
	private String		iconClass;
	@ApiModelProperty(value = "tree属性", name = "open")
	private Boolean		open;
	@ApiModelProperty(value = "创建人", name = "creator")
	private String		creator;
	@ApiModelProperty(value = "父级菜单名称", name = "createTime")
	private Timestamp	createTime;
	@ApiModelProperty(value = "最后修改人", name = "modifier")
	private String		modifier;
	@ApiModelProperty(value = "修改日期", name = "updateTime")
	private Timestamp	updateTime;
	@ApiModelProperty(value = "备注", name = "remark")
	private String		remark;
	@ApiModelProperty(value = "", name = "childrenList")
	private List<?>		childrenList;
}