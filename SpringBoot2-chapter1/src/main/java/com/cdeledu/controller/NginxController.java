package com.cdeledu.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/user/login/*")
public class NginxController {
	// 提供验证码
	@RequestMapping("verifyCode")
	public String verifyCode(HttpServletRequest request) {
		request.getSession().setAttribute("verifyCode", "N7GX");
		return request.getSession().getId() + ":" + request.getSession().getAttribute("verifyCode");
	}
	
	// 核对验证码
	@RequestMapping("checkVerifyCode")
	public String checkVerifyCode(HttpServletRequest request) {
		return request.getSession().getId() + ":" + request.getSession().getAttribute("verifyCode");
	}
}