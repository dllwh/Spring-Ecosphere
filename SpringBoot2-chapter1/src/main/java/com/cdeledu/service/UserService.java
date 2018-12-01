package com.cdeledu.service;

import java.util.List;

import com.cdeledu.model.rbac.SysUserVO;

public interface UserService {
	/** 获取用户总量 */
	Integer getUserCount();
	
	List<SysUserVO> getUserList();
}