package com.cdeledu.modules.system.service;

import java.util.List;

import com.cdeledu.modules.system.domain.SysMenu;

public interface MenuService {
	/**
	 * @方法描述: 保存菜单信息
	 */
	int saveMenu(SysMenu sysMenu);
	
	/**
	 * @方法描述:校验菜单名称是否唯一
	 */
	boolean checkMenuNameUnique(String menuName);
	
	/**
	 * @方法描述:查询菜单集合
	 */
	List<SysMenu> getSysMenuList();
	
	/**
	 * @方法描述:删除菜单管理信息
	 */
	int deleteMenuById(Integer menuId);
	
	/**
	 * @方法描述:根据菜单ID查询信息
	 */
	SysMenu getMenuById(Integer menuId);
	
	/**
	 * @方法描述:查询菜单数量
	 */
	int countMenuByParentId(Integer parentId);
	
	/**
	 * @方法描述:查询菜单使用数量
	 */
	int countRoleMenuByMenuId(Integer menuId);
}