package com.cdeledu.project.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述:登录验证
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年12月9日 下午7:23:43
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@Controller
public class LoginController {
	@ResponseBody
	@PostMapping("/login")
	public String ajaxLogin(String username, String password, Boolean rememberMe) {
		return "";
	}
	
	@GetMapping("/unauth")
	public String unauth() {
		return "/error/unauth";
	}
}
