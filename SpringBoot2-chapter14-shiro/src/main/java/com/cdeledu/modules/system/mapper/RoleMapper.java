package com.cdeledu.modules.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.cdeledu.modules.system.domain.SysRole;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: TODO(这里用一句话描述这个类的作用)
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年12月5日 下午10:45:36
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@Mapper
public interface RoleMapper {
	/**
	 * @方法描述 : 查询角色列表
	 * @return
	 */
	@Select("SELECT * FROM sys_role")
	List<SysRole> getRoleList();

	/**
	 * @方法描述 : 根据条件查询角色数据
	 * @param sysRole
	 * @return
	 */
	List<SysRole> getRoleList(SysRole sysRole);

	/**
	 * @方法描述 : 通过角色ID查询角色
	 * @param roleId
	 * @return
	 */
	@Select("SELECT * FROM sys_role WHERE id = #{id}")
	SysRole getRoleById(Integer roleId);

	/**
	 * @方法描述 : 通过角色ID删除角色
	 * @param roleId
	 * @return
	 */
	int deleteRoleById(Integer roleId);

	/**
	 * @方法描述 : 批量角色用户信息
	 * @param ids
	 *            需要删除的数据ID
	 * @return
	 */
	int batchDeleteRole(Integer[] ids);

	int countUserRoleByRoleId(Integer roleId);

	/**
	 * @方法描述 : 修改角色信息
	 * @param sysRole
	 * @return
	 */
	int updateRole(SysRole sysRole);

	/**
	 * @方法描述 : 新增角色信息
	 * @param sysRole
	 * @return
	 */
	int insertRole(SysRole sysRole);
}
