package com.cdeledu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.cdeledu.model.rbac.SysUserVO;
import com.cdeledu.service.UserService;

@Service
@SuppressWarnings("unchecked")
public class UserServiceImpl implements UserService {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Integer getUserCount() {
		return jdbcTemplate.queryForObject("select count(1) from sys_user", Integer.class);
	}

	@Override
	public List<SysUserVO> getUserList() {
		return (List<SysUserVO>) jdbcTemplate.queryForObject("select * from sys_user",
				SysUserVO.class);
	}
}
