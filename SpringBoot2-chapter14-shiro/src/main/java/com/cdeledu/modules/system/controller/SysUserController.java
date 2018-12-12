package com.cdeledu.modules.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 用户管理：用户是系统操作者，该功能主要完成系统用户配置
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年12月5日 下午10:38:27
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@Controller
@RequestMapping("sysUser")
public class SysUserController {
	private String prefix = "/system/sysUser";
	
	@GetMapping()
	public String index() {
		return prefix + "/index";
	}
}