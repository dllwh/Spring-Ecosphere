package com.cdeledu.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMapper {
	List<Map<String, Object>> getRoleList();
}