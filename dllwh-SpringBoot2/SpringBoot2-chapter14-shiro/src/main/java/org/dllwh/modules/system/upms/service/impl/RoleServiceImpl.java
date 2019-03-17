package org.dllwh.modules.system.upms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.dllwh.modules.system.upms.domain.SysRole;
import org.dllwh.modules.system.upms.mapper.RoleMapper;
import org.dllwh.modules.system.upms.service.RoleService;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述:
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年12月5日 下午10:49:40
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleMapper roleMapper;

	/**
	 * @方法描述 : 根据条件查询角色数据
	 * @param sysRole
	 * @return 角色数据集合信息
	 */
	@Override
	public List<SysRole> getRoleList(SysRole sysRole) {
		return roleMapper.getRoleList(sysRole);
	}

	/**
	 * @方法描述 : 通过角色ID查询角色
	 * @param roleId
	 * @return 角色对象信息
	 */
	@Override
	public SysRole getRoleById(Integer roleId) {
		return roleMapper.getRoleById(roleId);
	}

	/**
	 * @方法描述 : 通过角色ID删除角色
	 * @param roleId
	 * @return
	 */
	@Override
	public int deleteRoleById(Integer roleId) {
		return roleMapper.deleteRoleById(roleId);
	}

	/**
	 * @方法描述 : 批量删除角色用户信息
	 * @param ids
	 * @return
	 */
	@Override
	public int batchDeleteRole(Integer[] ids) {
		return roleMapper.batchDeleteRole(ids);
	}

	/**
	 * @方法描述 : 保存角色信息
	 * @param sysRole
	 * @return
	 */
	@Override
	public int saveRole(SysRole sysRole) {
		Integer roleId = sysRole.getId();
		if (roleId != null) {
			// 修改角色信息
			roleMapper.updateRole(sysRole);
		} else {
			roleMapper.insertRole(sysRole);
		}
		return 0;
	}

	/**
	 * @方法描述 : 通过角色ID查询角色使用数量
	 * @param roleId
	 * @return
	 */
	@Override
	public int countUserRoleByRoleId(Integer roleId) {
		return roleMapper.countUserRoleByRoleId(roleId);
	}

	/**
	 * @方法描述 :校验角色code是否唯一
	 */
	@Override
	public boolean checkRoleCodeUnique(String roleCode) {
		return roleMapper.checkRoleCodeUnique(roleCode) > 0 ? false : true;
	}
}