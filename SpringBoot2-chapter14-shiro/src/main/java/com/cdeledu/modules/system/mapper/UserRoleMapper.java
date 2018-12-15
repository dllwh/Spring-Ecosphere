package com.cdeledu.modules.system.mapper;

import java.util.List;

import com.cdeledu.modules.system.domain.SysUserRole;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述:
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年12月14日 下午5:13:27
 * @版本: V 0.1
 * @since: JDK 1.8
 */
public interface UserRoleMapper {
	/**
	 * @方法描述 : 通过用户ID删除用户和角色关联
	 * @param userId
	 * @return
	 */
	int deleteUserRoleByUserId(Integer userId);

	/**
	 * @方法描述 : 批量删除用户和角色关联
	 * @param ids
	 * @return
	 */
	int deleteUserRole(Integer[] ids);

	/**
	 * @方法描述 : 通过角色ID查询角色使用数量
	 * @param roleId
	 * @return
	 */
	int countUserRoleByRoleId(Integer roleId);

	/**
	 * @方法描述 : 批量新增用户角色信息
	 * @param userRoleList
	 * @return
	 */
	int batchUserRole(List<SysUserRole> userRoleList);
}
