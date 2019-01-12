package com.cdeledu.modules.system.upms.service;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.cdeledu.modules.system.upms.domain.SysUser;
import com.cdeledu.modules.system.upms.domain.SysUserRole;

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
	 * @方法描述:通过手机号查询用户
	 */
	SysUser getUserByPhoneNumber(String phoneNumber);

	/**
	 * @方法描述:通过邮件查询用户
	 */
	SysUser getUserByEmail(String email);

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
	Integer batchDeleteUser(Integer[] userIds);

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
	Set<String> getUserRoleGroup(Integer userId);

	/**
	 * @方法描述 : 通过用户ID查询角色使用数量
	 */
	int countUserRoleByUserId(Integer userId);

	/**
	 * 
	 * @方法描述:创建用户-角色 关联
	 */
	int saveUserRole(Integer userId, Integer[] roleIds);

	int deleteUserRoleByUserId(@Param("userId") Integer userId);

	/**
	 * @方法描述 : 根据用户获取角色列表
	 */
	List<SysUserRole> getRoleByUser(Integer userId);
}