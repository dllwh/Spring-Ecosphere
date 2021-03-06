package org.dllwh.modules.system.upms.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import org.dllwh.common.RestResult;
import org.dllwh.common.constant.ShiroConstants;
import org.dllwh.framework.controller.BaseController;
import org.dllwh.framework.shiro.ShiroHelper;
import org.dllwh.framework.shiro.service.LoginService;
import org.dllwh.modules.system.upms.domain.SysUser;

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
 * @版本: V1.0.4
 * @since: JDK 1.8
 */
@Controller
@Api(tags = "登录验证")
@RequestMapping("login")
public class LoginController extends BaseController {

	@Autowired
	private LoginService loginService;

	@GetMapping({ "", "/" })
	public String login() {
		SysUser sysUser = getUser();
		if(sysUser == null){
			return "index";
		} else {
			return ShiroConstants.SUCCESS_URL;
		}
	}

	@ResponseBody
	@ApiOperation(value = "登录")
	@RequestMapping(value = "ajaxLogin", method = { RequestMethod.GET, RequestMethod.POST })
	@ApiImplicitParams({ @ApiImplicitParam(name = "userName", value = "登录账号", dataType = "String"),
			@ApiImplicitParam(name = "password", value = "登录密码", dataType = "String"),
			@ApiImplicitParam(name = "code", value = "验证码", dataType = "String"),
			@ApiImplicitParam(name = "rememberMe", value = "是否记住我", dataType = "boolean") })
	public RestResult ajaxLogin(HttpServletRequest request,
			@RequestParam(name = "userName", required = true) String userName,
			@RequestParam(name = "password", required = true) String password,
			@RequestParam(name = "code") String code, 
			@RequestParam(value="rememberMe",required = false) boolean rememberMe) throws Exception {

		if (StringUtils.isBlank(userName)) { // 用户名为空 错误
			throw new HttpMessageNotReadableException("用户名不能为空");
		}

		if (StringUtils.isBlank(password)) { // 密码为空 错误
			throw new HttpMessageNotReadableException("密码不能为空");
		}

		boolean codeFlag = false;
		Cookie[] cookie = request.getCookies();
		for (Cookie cook : cookie) {
			if (cook.getName().equalsIgnoreCase("AdminCode")) {
				if (cook.getValue().equalsIgnoreCase(code)) {
					codeFlag = true;
					break;
				}
			}
		}

		if (!codeFlag) {
			return RestResult.error(222, "验证码错误");
		}

		String msg = "用户名或密码错误,请重新登录!";
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

	@ResponseBody
	@ApiOperation(value = "登录验证")
	@GetMapping(value = "checkuser")
	public RestResult checkuser() {
		SysUser sysUser = ShiroHelper.getCurrenLoginUser();
		if (sysUser == null) {
			return RestResult.error(10010,"由于长时间未操作，请重新登录");
		}
		return RestResult.success();
	}

	@ResponseBody
	@RequestMapping(value = "notLogin", method = RequestMethod.GET)
	public RestResult notLogin() {
		return RestResult.error(10010,"您尚未登陆！");
	}

	@RequestMapping(value = "notRole", method = RequestMethod.GET)
	public RestResult notRole() {
		return RestResult.error("您没有权限！");
	}

	@ResponseBody
	@ApiOperation(value = "退出")
	@GetMapping(value = "doLogout")
	public RestResult doLogout() {
		ShiroHelper.logout();
		return RestResult.success("成功注销！");
	}

	@GetMapping(value = "/kickout")
	@ApiOperation(value = "被踢出后跳转的页面")
	public String kickOut() {
		return "kickout";
	}
}
