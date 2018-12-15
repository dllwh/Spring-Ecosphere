package com.cdeledu.modules.system.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdeledu.common.RestResult;

import io.swagger.annotations.Api;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述:登录控制器
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年12月9日 下午7:23:43
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@RestController
@Api(tags = "登录验证")
@RequestMapping("login")
public class LoginController {
	/**
	 * @方法描述:登录
	 * @param username
	 *            登录账号
	 * @param password
	 *            登录密码
	 * @param rememberMe
	 *            是否记住我
	 * @return
	 */
	@PostMapping(value = "ajaxLogin")
	public String ajaxLogin(String username, String password, Boolean rememberMe) {
		return "";
	}
	
	/**
	 * @方法描述:登录验证
	 * @return
	 */
	@RequestMapping(value = "checkuser")
	public RestResult checkuser() {
		return RestResult.success();
	}
	
	/**
	 * @方法描述:退出
	 * @return
	 */
	@RequestMapping(value = "doLogout")
	public RestResult doLogout() {
		return RestResult.success();
	}
}
