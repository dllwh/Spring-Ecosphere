package org.dllwh.service;

import java.util.List;

import org.dllwh.model.rbac.SysUserVO;

public interface UserService {
	/** 获取用户总量 */
	Integer getUserCount();
	
	List<SysUserVO> getUserList();
}