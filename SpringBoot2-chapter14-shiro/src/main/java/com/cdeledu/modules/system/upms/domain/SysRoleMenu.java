package com.cdeledu.modules.system.upms.domain;

import lombok.Data;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 角色{@link SysRole}与权限{@link SysMenu}中间表实体类
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年12月12日 下午11:55:48
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@Data
public class SysRoleMenu {
	/** 角色ID */
	private Integer	roleId;
	/** 菜单ID */
	private Integer	menuId;
}