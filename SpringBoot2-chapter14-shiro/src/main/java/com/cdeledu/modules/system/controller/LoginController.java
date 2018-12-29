package com.cdeledu.modules.system.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.cdeledu.common.RestResult;
import com.cdeledu.framework.shiro.service.LoginService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

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

	@ApiOperation(value = "登录")
	@RequestMapping(value = "ajaxLogin", method = { RequestMethod.GET, RequestMethod.POST })
	@ApiImplicitParams({ @ApiImplicitParam(name = "userName", value = "登录账号", dataType = "String"),
			@ApiImplicitParam(name = "password", value = "登录密码", dataType = "String"),
			@ApiImplicitParam(name = "rememberMe", value = "是否记住我", dataType = "boolean") })
	public RestResult ajaxLogin(String userName, String password, Boolean rememberMe) throws Exception {

		if (StringUtils.isBlank(userName)) { // 用户名为空 错误
			throw new HttpMessageNotReadableException("用户名不能为空");
		}

		if (StringUtils.isBlank(password)) { // 密码为空 错误
			throw new HttpMessageNotReadableException("密码不能为空");
		}
		String msg = "用户或密码错误";
		try {
			JSONObject loginRespnse = JSONObject.parseObject(loginService.login(userName, password));
			if (loginRespnse.getBoolean("result") == true) {
				return RestResult.success("登录成功");
			} else {
				return RestResult.error(String.valueOf(loginRespnse.getString("msg")));
			}
		} catch (AuthenticationException e) {
			if (StringUtils.isNotEmpty(e.getMessage())) {
				msg = e.getMessage();
			}
			return RestResult.error(msg);
		}
	}

	@ApiOperation(value = "登录验证")
	@GetMapping(value = "checkuser")
	public RestResult checkuser() {
		return RestResult.success();
	}

	@ApiOperation(value = "退出")
	@GetMapping(value = "doLogout")
	public RestResult doLogout() {
		return RestResult.success();
	}
}