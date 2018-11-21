package com.cdeledu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.cdeledu.dao.UserMapper;
import com.cdeledu.model.rbac.SysUserVO;
import com.cdeledu.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private UserMapper userMapper;

	@Override
	public Integer getUserCount() {
		return jdbcTemplate.queryForObject("select count(1) from sys_user", Integer.class);
	}

	@Override
	public List<SysUserVO> getUserList() {
		return userMapper.getUserList();
	}
}
