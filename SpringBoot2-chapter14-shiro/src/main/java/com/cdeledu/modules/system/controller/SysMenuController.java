package com.cdeledu.modules.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;

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
	private String prefix = "system/menu";

	@GetMapping()
	public String index() {
		return prefix + "/index";
	}
}