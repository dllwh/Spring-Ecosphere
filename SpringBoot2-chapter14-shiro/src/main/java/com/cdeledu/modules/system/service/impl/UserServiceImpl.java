package com.cdeledu.modules.system.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.cdeledu.modules.system.domain.SysUser;
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

	@Override
	public List<SysUser> getUserList(SysUser sysUser) {
		return null;
	}

	@Override
	public SysUser selectUserByLoginName(String userName) {
		return null;
	}

	@Override
	public SysUser selectUserById(Integer userId) {
		return null;
	}

	@Override
	public int deleteUserById(Integer userId) {
		return 0;
	}

	@Override
	public int batchDeleteUser(Integer[] ids) {
		return 0;
	}

	@Override
	public int saveUser(SysUser sysUser) {
		return 0;
	}

	@Override
	public int updateUser(SysUser sysUser) {
		return 0;
	}

	@Override
	public int resetUserPwd(SysUser sysUser) {
		return 0;
	}

	@Override
	public String checkLoginNameUnique(String loginName) {
		return null;
	}

	@Override
	public Set<String> selectUserRoleGroup(Integer userId) {
		return null;
	}
}