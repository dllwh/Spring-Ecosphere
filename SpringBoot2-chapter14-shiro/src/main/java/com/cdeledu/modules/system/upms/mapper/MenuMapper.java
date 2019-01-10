package com.cdeledu.modules.system.upms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.cdeledu.modules.system.upms.domain.SysMenu;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述:
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年12月5日 下午10:45:47
 * @版本: V1.0.1
 * @since: JDK 1.8
 */

@Mapper
public interface MenuMapper {
	/**
	 * @方法描述:新增菜单信息
	 */
	int insertMenu(SysMenu sysMenu);

	/**
	 * @方法描述:修改菜单信息
	 */
	int updateMenu(SysMenu sysMenu);

	/**
	 * @方法描述:校验菜单名称是否唯一
	 */
	int checkMenuNameUnique(String menuName);

	@Select("SELECT * FROM sys_menu")
	List<SysMenu> getSysMenuList();

	@Update("update sys_menu set ifEnabled = 0 where id = #{id}")
	int deleteMenuById(Integer menuId);

	@Select("SELECT * FROM sys_menu WHERE id = #{id}")
	SysMenu getMenuById(Integer menuId);

	@Select("SELECT count(*) FROM sys_menu WHER parentCode = #{id}")
	int countMenuByParentId(Integer parentId);

	@Select("SELECT COUNT(*) FROM sys_upms_role_menu WHERE menuId = #{menuId}")
	int countRoleMenuByMenuId(Integer menuId);
}
