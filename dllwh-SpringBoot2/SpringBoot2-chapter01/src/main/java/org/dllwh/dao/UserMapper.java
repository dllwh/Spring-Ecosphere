package org.dllwh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import org.dllwh.model.rbac.SysUserVO;

@Mapper
public interface UserMapper {
	@Select("SELECT * from sys_upms_user")
	List<SysUserVO> getUserList();
}