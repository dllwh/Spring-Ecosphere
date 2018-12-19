package com.cdeledu.modules.system.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
@Mapper
public interface UserRoleMapper {
	/**
	 * @方法描述:根据用户ID查询用户所属角色
	 */
	List<SysUserRole> getRolesByUserId(Integer userId);

	/**
	 * @方法描述:根据用户ID查询用户所属角色名字
	 */
	Set<String> getUserRoleGroup(Integer userId);

	/**
	 * @方法描述 : 通过用户ID删除用户和角色关联
	 * @param userId
	 * @return
	 */
	@Delete("DELETE FROM sys_upms_user_role WHERE userid = #{userId}")
	int deleteUserRoleByUserId(@Param("userId") Integer userId);
	
	/**
	 * @方法描述 : 通过角色ID删除用户和角色关联
	 * @param userId
	 * @return
	 */
	@Delete("DELETE FROM sys_upms_user_role WHERE roleId = #{roleId}")
	int deleteUserRoleByRoleId(@Param("roleId") Integer roleId);
	
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
	@Select("SELECT COUNT(*) FROM sys_upms_user_role WHERE roleId = #{roleId}")
	int countUserRoleByRoleId(Integer roleId);

	/**
	 * @方法描述 : 通过角色ID查询角色使用数量
	 * @param roleId
	 * @return
	 */
	@Select("SELECT COUNT(*) FROM sys_upms_user_role WHERE userId = #{userId}")
	int countUserRoleByUserId(Integer userId);

	/**
	 * @方法描述 : 创建 用户-角色关联关系
	 */
	@Insert("INSERT INTO sys_upms_user_role(userId,roleId) VALUES(#{userId},#{roleId})")
	int saveRoleUser(@Param("userId") Integer userId, @Param("roleId") Integer roleId);
	
	/**
	 * @方法描述 : 批量新增用户角色信息
	 * @param userRoleList
	 * @return
	 */
	int batchUserRole(List<SysUserRole> userRoleList);
}