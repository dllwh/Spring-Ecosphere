package com.cdeledu.modules.system.service;

import java.util.List;
import java.util.Set;

import com.cdeledu.modules.system.domain.SysUser;

public interface UserService {
	/**
	 * @方法描述:根据条件查询用户对象
	 */
	List<SysUser> getUserList(SysUser sysUser);
	
	/**
	 * @方法描述:通过用户名查询用户
	 */
	SysUser getUserByLoginName(String userName);
	
	/**
	 * @方法描述:通过用户ID查询用户
	 */
	SysUser getUserById(Integer userId);
	
	/**
	 * @方法描述:通过用户ID删除用户
	 */
	int deleteUserById(Integer userId);
	
	/**
	 * @方法描述:批量删除用户信息
	 */
	Integer batchDeleteUser(Integer[] ids);
	
	/**
	 * @方法描述:保存用户信息
	 */
	Integer saveUser(SysUser sysUser);
	
	/**
	 * @方法描述:修改用户信息
	 */
	Integer updateUser(SysUser sysUser);
	
	/**
	 * @方法描述: 修改用户密码信息
	 */
	Integer resetUserPwd(SysUser sysUser);
	
	/**
	 * @方法描述: 校验用户名称是否唯一
	 */
	boolean checkLoginNameUnique(String loginName);
	
	/**
	 * @方法描述:根据用户ID查询用户所属角色
	 */
	Set<String> selectUserRoleGroup(Integer userId);
}
