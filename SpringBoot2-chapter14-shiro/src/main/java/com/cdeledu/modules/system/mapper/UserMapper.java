package com.cdeledu.modules.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.cdeledu.modules.system.domain.SysUser;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: TODO(这里用一句话描述这个类的作用)
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年12月5日 下午10:45:21
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@Mapper
public interface UserMapper {
	/**
	 * @方法描述:根据条件查询用户对象
	 */
	List<SysUser> getUserList(SysUser sysUser);

	/**
	 * @方法描述 : 根据条件查询用户对象
	 */
	int getUserListCount(SysUser sysUser);

	/**
	 * @方法描述:通过用户名查询用户
	 */
	@Select("SELECT * FROM sys_upms_user WHERE userName = #{userName}")
	SysUser selectUserByLoginName(@Param(value = "userName") String userName);

	/**
	 * @方法描述:通过手机号查询用户
	 */
	@Select("SELECT * FROM sys_upms_user WHERE mobile = #{phoneNumber}")
	SysUser getUserByPhoneNumber(@Param(value = "phoneNumber") String phoneNumber);

	/**
	 * @方法描述:通过邮件查询用户
	 */
	@Select("SELECT * FROM sys_upms_user WHERE email = #{email}")
	SysUser getUserByEmail(@Param(value = "email") String email);

	/**
	 * @方法描述:通过用户ID查询用户
	 */
	@Select("SELECT * FROM sys_upms_user WHERE id = #{userId}")
	SysUser selectUserById(@Param(value = "userId") Integer userId);

	/**
	 * @方法描述:通过用户ID删除用户
	 */
	@Delete("delete from sys_upms_user where id = #{userId} and id != 1")
	int deleteUserById(Integer userId);

	/**
	 * @方法描述:批量删除用户信息
	 */
	int batchDeleteUser(Integer[] ids);

	/**
	 * @方法描述:新增用户信息
	 */
	int insertUser(SysUser sysUser);

	/**
	 * @方法描述:修改用户信息
	 */
	int updateUser(SysUser sysUser);

	/**
	 * @方法描述: 校验用户名称是否唯一
	 */
	@Select("SELECT count(*) FROM sys_upms_user WHERE userName = #{loginName}")
	int checkLoginNameUnique(@Param("loginName") String loginName);
}
