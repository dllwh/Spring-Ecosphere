package com.cdeledu.modules.system.upms.domain;

import lombok.Data;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 用户{@link SysUser}角色{@link SysRole}实体类
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年12月12日 下午11:56:41
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@Data
public class SysUserRole {
	/** 管理员id */
	private Integer	userId;
	/** 登录名 */
	private String	userName;
	/** 用户个数 */
	private Integer	userCount;
	/** 角色id */
	private Integer	roleId;
	/** 角色名称 */
	private String	roleName;
	/** 角色编码 */
	private String	roleCode;
}