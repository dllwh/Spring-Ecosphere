package com.cdeledu.modules.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cdeledu.common.RestResult;
import com.cdeledu.modules.system.domain.SysMenu;
import com.cdeledu.modules.system.service.MenuService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 配置系统菜单，操作权限，按钮权限标识等
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年12月5日 下午10:38:48
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@Controller
@RequestMapping("/system/menu")
@Api(tags = "系统菜单，操作权限")
public class SysMenuController {
	private String		prefix	= "system/menu";
	@Autowired
	private MenuService	menuService;
						
	@GetMapping()
	public String index() {
		return prefix + "/index";
	}
	
	@GetMapping(value = "getList")
	@ApiOperation(value = "")
	@ResponseBody
	public List<SysMenu> getList() {
		return menuService.getSysMenuList();
	}
	
	@ResponseBody
	@PostMapping(value = "save")
	@ApiOperation(value = "菜单管理-保存菜单(包括创建、更新)")
	public RestResult save(SysMenu sysMenu) {
		if (menuService.saveMenu(sysMenu) > 0) {
			return RestResult.success();
		} else {
			return RestResult.error();
		}
	}
	
	@ResponseBody
	@DeleteMapping(value = "remove/{menuId}")
	@ApiOperation(value = "菜单管理-删除菜单")
	public RestResult remove(@PathVariable("menuId") Integer menuId) {
		if (menuService.countMenuByParentId(menuId) > 0) {
			return RestResult.error("存在子菜单,不允许删除");
		}
		
		if (menuService.countRoleMenuByMenuId(menuId) > 0) {
			return RestResult.error("菜单已分配,不允许删除");
		}
		
		if (menuService.deleteMenuById(menuId) > 0) {
			return RestResult.success();
		}
		return RestResult.error();
	}
}