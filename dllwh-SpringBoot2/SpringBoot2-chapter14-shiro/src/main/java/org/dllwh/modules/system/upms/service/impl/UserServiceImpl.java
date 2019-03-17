package org.dllwh.modules.system.upms.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.dllwh.modules.system.upms.domain.SysUser;
import org.dllwh.modules.system.upms.domain.SysUserRole;
import org.dllwh.modules.system.upms.mapper.UserMapper;
import org.dllwh.modules.system.upms.mapper.UserRoleMapper;
import org.dllwh.modules.system.upms.service.UserService;
import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;

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
@Slf4j
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

	@Override
	public SysUser getUserByLoginName(String userName) {
		return userMapper.selectUserByLoginName(userName);
	}

	@Override
	public SysUser getUserByPhoneNumber(String phoneNumber) {
		return userMapper.getUserByPhoneNumber(phoneNumber);
	}

	@Override
	public SysUser getUserByEmail(String email) {
		return userMapper.getUserByEmail(email);
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
			sysUser.setUserName("");// 不能更新userName
			count = updateUser(sysUser);
		} else {
			// 新增用户信息
			count = userMapper.insertUser(sysUser);
			log.info("新增用户，主键：userId={}", sysUser.getId());
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
	 * 校验用户名称是否唯一
	 */
	@Override
	public boolean checkLoginNameUnique(String loginName) {
		int count = userMapper.checkLoginNameUnique(loginName);
		if (count > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 查询用户所属角色组
	 */
	@Override
	public Set<String> getUserRoleGroup(Integer userId) {
		return userRoleMapper.getUserRoleGroup(userId);
	}

	/**
	 * 通过用户ID查询角色使用数量
	 */
	@Override
	public int countUserRoleByUserId(Integer userId) {
		return userRoleMapper.countUserRoleByUserId(userId);
	}

	@Override
	public int saveUserRole(Integer userId, Integer[] roleIds) {

		List<SysUserRole> userRoleList = Lists.newArrayList();
		for (Integer roleId : roleIds) {
			SysUserRole sysUserRole = new SysUserRole();
			sysUserRole.setUserId(userId);
			sysUserRole.setRoleId(roleId);
		}
		if (userRoleList != null && userRoleList.size() > 0) {
			userRoleMapper.deleteUserRoleByUserId(userId);
			return userRoleMapper.batchInsertUserRole(userRoleList);
		} else {
			return 0;
		}
	}

	@Override
	public int deleteUserRoleByUserId(Integer userId) {
		return userRoleMapper.deleteUserRoleByUserId(userId);
	}

	/**
	 * 根据用户获取角色列表
	 */
	@Override
	public List<SysUserRole> getRoleByUser(Integer userId) {
		return userRoleMapper.getRolesByUserId(userId);
	}
}