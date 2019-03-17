package org.dllwh.modules.system.upms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import org.dllwh.modules.system.upms.domain.SysRoleMenu;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 角色与菜单关联表 数据层
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年12月14日 下午5:06:53
 * @版本: V 0.1
 * @since: JDK 1.8
 */
@Mapper
public interface RoleMenuMapper {

	/**
	 * @方法描述 : 保存角色、菜单关联
	 * @param roleId
	 * @param menuID
	 * @return
	 */
	@Insert("INSERT INTO sys_upms_role_menu(roleId,menuId) VALUES (#{roleId},#{menuID});")
	@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id") 
	int saveRoleAccess(Integer roleId, Integer menuID);

	/**
	 * @方法描述 : 通过角色ID删除角色和菜单关联
	 * @param roleId
	 * @return
	 */
	@Delete("delete from sys_upms_role_menu where roleId=#{roleId}")
	int deleteRoleMenuByRoleId(Long roleId);

	/**
	 * @方法描述 : 批量角色角色菜单关联信息
	 * @param ids
	 * @return
	 */
	int deleteRoleMenu(Long[] ids);

	/**
	 * @方法描述 : 查询菜单使用数量
	 * @param menuId
	 * @return
	 */
	@Select("select count(*) from sys_upms_role_menu where menuId=#{menuId}  ")
	int getCountRoleMenuByMenuId(Long menuId);

	/**
	 * @方法描述 : 批量新增角色菜单信息
	 * @param roleMenuList
	 * @return
	 */
	int batchRoleMenu(List<SysRoleMenu> roleMenuList);
}
