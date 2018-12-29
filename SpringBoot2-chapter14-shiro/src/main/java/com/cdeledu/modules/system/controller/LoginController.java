package com.cdeledu.modules.system.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdeledu.common.RestResult;
import com.cdeledu.framework.shiro.service.LoginService;

import io.swagger.annotations.Api;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述:登录控制器
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年12月9日 下午7:23:43
 * @版本: V1.0.2
 * @since: JDK 1.8
 */
@RestController
@Api(tags = "登录验证")
@RequestMapping("login")
public class LoginController {

	@Autowired
	private LoginService loginService;

	/**
	 * @方法描述:登录
	 * @param username
	 *            登录账号
	 * @param password
	 *            登录密码
	 * @param rememberMe
	 *            是否记住我
	 * @return
	 * @throws MissingServletRequestParameterException
	 */
	@PostMapping(value = "ajaxLogin")
	public RestResult ajaxLogin(String userName, String password, Boolean rememberMe)
			throws Exception {

		if (StringUtils.isBlank(userName)) { // 用户名为空 错误
			throw new MissingServletRequestParameterException("userName", "String");
		}

		if (StringUtils.isBlank(password)) { // 密码为空 错误
			throw new MissingServletRequestParameterException("password", "String");
		}

		try {
			loginService.login(userName, password);
		} catch (AuthenticationException e) {
			String msg = "用户或密码错误";
			if (StringUtils.isNotEmpty(e.getMessage())) {
				msg = e.getMessage();
			}
			return RestResult.error(msg);
		}
		return RestResult.success("登录成功");
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
