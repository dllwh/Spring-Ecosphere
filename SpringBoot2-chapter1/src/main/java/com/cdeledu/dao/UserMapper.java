package com.cdeledu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.cdeledu.model.rbac.SysUserVO;

@Mapper
public interface UserMapper {
	@Select("SELECT * from sys_upms_user")
	List<SysUserVO> getUserList();
}