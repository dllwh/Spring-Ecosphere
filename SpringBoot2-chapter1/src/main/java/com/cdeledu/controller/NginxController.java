package com.cdeledu.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: SpringBoot 实现前后端分离的跨域访问（Nginx）
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年10月22日 下午4:10:24
 * @版本: V1.0
 * @since: JDK 1.8
 */
@RestController
@RequestMapping("/user/login/")
@Api(tags = "实现前后端分离的跨域访问")
public class NginxController {
	// 提供验证码
	@RequestMapping("verifyCode")
	@ApiOperation(value = "提供验证码",notes="根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
	public String verifyCode(HttpServletRequest request) {
		request.getSession().setAttribute("verifyCode", "N7GX");
		return request.getSession().getId() + ":" + request.getSession().getAttribute("verifyCode");
	}
	
	@RequestMapping("checkVerifyCode")
	@ApiOperation(value="核对验证码")
	public String checkVerifyCode(HttpServletRequest request) {
		return request.getSession().getId() + ":" + request.getSession().getAttribute("verifyCode");
	}
}