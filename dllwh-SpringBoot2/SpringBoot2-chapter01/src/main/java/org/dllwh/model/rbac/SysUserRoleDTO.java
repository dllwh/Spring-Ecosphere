package org.dllwh.model.rbac;

import lombok.Data;

@Data
public class SysUserRoleDTO {
	private Integer	userId;		// 管理员id
	private String	userName;	// 登录名
	private Integer	userCount;	// 用户个数
	private Integer	roleId;		// 角色id
	private String	roleName;	// 角色名称
	private String	roleCode;	// 角色编码
}