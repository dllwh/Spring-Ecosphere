package org.dllwh.model.rbac;

import java.io.Serializable;

import lombok.Data;

@Data
public class SysUserRoleDTO implements Serializable {

	private static final long serialVersionUID = -747625094926250378L;
	/** 管理员id */
	private Integer           userId;
	/** 登录名 */
	private String            userName;
	/** 用户个数 */
	private Integer           userCount;
	/** 角色id */
	private Integer           roleId;
	/** 角色名称 */
	private String            roleName;
	/** 角色编码 */
	private String            roleCode;
}
