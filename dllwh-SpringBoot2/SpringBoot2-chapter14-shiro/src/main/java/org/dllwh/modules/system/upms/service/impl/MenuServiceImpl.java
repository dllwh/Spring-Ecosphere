package org.dllwh.modules.system.upms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.dllwh.framework.shiro.ShiroHelper;
import org.dllwh.modules.system.upms.domain.SysMenu;
import org.dllwh.modules.system.upms.mapper.MenuMapper;
import org.dllwh.modules.system.upms.mapper.RoleMenuMapper;
import org.dllwh.modules.system.upms.service.MenuService;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述:
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年12月5日 下午10:49:25
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@Service
public class MenuServiceImpl implements MenuService {
	@Autowired
	private MenuMapper		menuMapper;
	@Autowired
	private RoleMenuMapper	roleMenuMapper;

	/**
	 * 保存菜单信息
	 */
	@Override
	public int saveMenu(SysMenu sysMenu) {
		if (sysMenu.getParentCode() == null) { // 主目录也就是根目录
			sysMenu.setParentCode(-1);
			int menuId = menuMapper.insertMenu(sysMenu);
			// 分配给超级管理员
			roleMenuMapper.saveRoleAccess(1, menuId);
		} else {
			return menuMapper.updateMenu(sysMenu);
		}
		return 0;
	}

	/**
	 * 校验菜单名称是否唯一
	 */
	@Override
	public boolean checkMenuNameUnique(String menuName) {
		int result = menuMapper.checkMenuNameUnique(menuName);
		return result > 0 ? false : true;
	}

	/**
	 * 查询所有菜单信息
	 */
	@Override
	public List<SysMenu> getSysMenuList() {
		return menuMapper.getSysMenuList();
	}

	/**
	 * 删除菜单管理信息
	 */
	@Override
	public int deleteMenuById(Integer menuId) {
		ShiroHelper.clearCachedAuthorizationInfo();
		return menuMapper.deleteMenuById(menuId);
	}

	/**
	 * 根据菜单ID查询信息
	 */
	@Override
	public SysMenu getMenuById(Integer menuId) {
		return menuMapper.getMenuById(menuId);
	}

	/**
	 * 查询菜单数量
	 */
	@Override
	public int countMenuByParentId(Integer parentId) {
		return menuMapper.countMenuByParentId(parentId);
	}

	/**
	 * 查询菜单使用数量
	 */
	@Override
	public int countRoleMenuByMenuId(Integer parentId) {
		return menuMapper.countRoleMenuByMenuId(parentId);
	}
}