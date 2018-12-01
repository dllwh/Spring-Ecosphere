package com.cdeledu.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdeledu.dao.RoleMapper;
import com.cdeledu.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleMapper roleMapper;
	
	@Override
	public List<Map<String, Object>> getRoleList() {
		return roleMapper.getRoleList();
	}
}