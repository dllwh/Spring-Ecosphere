package com.cdeledu.modules.system.service;

import java.util.List;
import java.util.Set;

import com.cdeledu.modules.system.domain.SysUser;

public interface UserService {
	List<SysUser> getUserList(SysUser sysUser);

	SysUser selectUserByLoginName(String userName);

	SysUser selectUserById(Integer userId);

	int deleteUserById(Integer userId);

	int batchDeleteUser(Integer[] ids);

	int saveUser(SysUser sysUser);

	int updateUser(SysUser sysUser);

	int resetUserPwd(SysUser sysUser);

	String checkLoginNameUnique(String loginName);

	Set<String> selectUserRoleGroup(Integer userId);
}
