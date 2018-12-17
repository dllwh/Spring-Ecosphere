package com.cdeledu.modules.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdeledu.modules.system.domain.SysUser;
import com.cdeledu.modules.system.domain.SysUserRole;
import com.cdeledu.modules.system.mapper.UserMapper;
import com.cdeledu.modules.system.mapper.UserRoleMapper;
import com.cdeledu.modules.system.service.UserService;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述:
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年12月5日 下午10:49:57
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper		userMapper;
	@Autowired
	private UserRoleMapper	userRoleMapper;
							
	/**
	 * 根据条件查询用户对象
	 */
	@Override
	public List<SysUser> getUserList(SysUser sysUser) {
		return userMapper.getUserList(sysUser);
	}
	
	/**
	 * 通过用户名查询用户
	 */
	@Override
	public SysUser getUserByLoginName(String userName) {
		return userMapper.selectUserByLoginName(userName);
	}
	
	/**
	 * 通过用户ID查询用户
	 */
	@Override
	public SysUser getUserById(Integer userId) {
		return userMapper.selectUserById(userId);
	}
	
	/**
	 * 通过用户ID删除用户
	 */
	@Override
	public int deleteUserById(Integer userId) {
		// 删除用户与角色关联
		userRoleMapper.deleteUserRoleByUserId(userId);
		return userMapper.deleteUserById(userId);
	}
	
	/**
	 * 批量删除用户信息
	 */
	@Override
	public Integer batchDeleteUser(Integer[] ids) {
		userRoleMapper.deleteUserRole(ids);
		return userMapper.batchDeleteUser(ids);
	}
	
	/**
	 * 保存用户信息
	 * 
	 */
	@Override
	public Integer saveUser(SysUser sysUser) {
		Integer userId = sysUser.getId();
		int count = 0;
		if (userId != null) {
			// 修改用户信息
			count = updateUser(sysUser);
			// 删除用户与角色关联
			userRoleMapper.deleteUserRoleByUserId(userId);
			// 新增用户与角色管理
			insertUserRole(sysUser);
		} else {
			// 新增用户信息
			count = userMapper.insertUser(sysUser);
			// 新增用户与角色管理
			insertUserRole(sysUser);
		}
		return count;
	}
	
	/**
	 * 修改用户信息
	 */
	@Override
	public Integer updateUser(SysUser sysUser) {
		return userMapper.updateUser(sysUser);
	}
	
	/**
	 * 修改用户密码
	 */
	@Override
	public Integer resetUserPwd(SysUser sysUser) {
		/* 需要处理 */
		return updateUser(sysUser);
	}
	
	/**
	 * 新增用户角色信息
	 */
	public void insertUserRole(SysUser sysUser) {
		// 新增用户与角色管理
		List<SysUserRole> userRoleList = new ArrayList<SysUserRole>();
		for (Integer roleId : sysUser.getRoleIds()) {
			SysUserRole userRole = new SysUserRole();
			userRole.setUserId(sysUser.getId());
			userRole.setRoleId(roleId);
			userRoleList.add(userRole);
		}
		if (userRoleList != null & userRoleList.size() > 0) {
			userRoleMapper.batchUserRole(userRoleList);
		}
	}
	
	/**
	 * 校验用户名称是否唯一
	 */
	@Override
	public boolean checkLoginNameUnique(String loginName) {
		int count = userMapper.checkLoginNameUnique(loginName);
		if (count > 0) {
			return false;
		}
		return true;
	}
	
	/**
	 * 查询用户所属角色组
	 */
	@Override
	public Set<String> selectUserRoleGroup(Integer userId) {
		return userRoleMapper.getUserRoleGroup(userId);
	}
}