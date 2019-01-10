package com.cdeledu.modules.system.upms.service;

import java.util.List;

import com.cdeledu.modules.system.upms.domain.SysRole;

public interface RoleService {
	
	/**
	 * @方法描述 : 根据条件查询角色数据
	 */
	List<SysRole> getRoleList(SysRole sysRole);
	
	/**
	 * @方法描述 : 通过角色ID查询角色
	 */
	SysRole getRoleById(Integer roleId);
	
	/**
	 * @方法描述 : 通过角色ID删除角色
	 */
	int deleteRoleById(Integer roleId);
	
	/**
	 * @方法描述 : 批量删除角色用户信息
	 */
	int batchDeleteRole(Integer[] ids);
	
	/**
	 * @方法描述 : 保存角色信息
	 */
	int saveRole(SysRole sysRole);
	
	/**
	 * @方法描述 : 通过角色ID查询角色使用数量
	 */
	int countUserRoleByRoleId(Integer roleId);
	
	/**
	 * @方法描述 : 校验角色code是否唯一
	 */
	boolean checkRoleCodeUnique(String roleCode);
}