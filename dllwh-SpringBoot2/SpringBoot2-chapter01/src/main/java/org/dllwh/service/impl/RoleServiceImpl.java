package org.dllwh.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.dllwh.dao.RoleMapper;
import org.dllwh.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleMapper roleMapper;
	
	@Override
	public List<Map<String, Object>> getRoleList() {
		return roleMapper.getRoleList();
	}
}