package com.cdeledu.modules.system.service;

import java.util.List;

import com.cdeledu.modules.system.domain.SysRole;

public interface RoleService {
	List<SysRole> getRoleList();
	
	List<SysRole> getRoleList(SysRole sysRole);
	
	SysRole getRoleById(Integer roleId);
	
	int deleteRoleById(Integer roleId);
	
	int batchDeleteRole(Integer[] ids);
	
	int saveRole(SysRole sysRole);
	
	int countUserRoleByRoleId(Integer roleId);
}
