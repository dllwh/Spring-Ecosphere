package com.cdeledu.modules.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 角色菜单权限分配、设置角色进行数据范围权限划分
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年12月5日 下午10:39:15
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@Controller
@RequestMapping("/system/role")
@Api(tags = "角色管理")
public class SysRoleController {
	private String prefix = "system/role";

	@GetMapping()
	public String index() {
		return prefix + "/index";
	}
}