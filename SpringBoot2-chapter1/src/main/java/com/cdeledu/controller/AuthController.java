package com.cdeledu.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdeledu.core.baseBase.RestResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 认证接口
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年11月28日 上午9:14:12
 * @版本: V 0.1
 * @since: JDK 1.8
 */
@RestController
@Api(tags = "认证接口")
public class AuthController {

	
	@GetMapping("/getCurrentUser")
	@ApiOperation("获取当前用户信息")
	public Principal getCurrentUser(Principal principal) {
		return principal;
	}
	
	@PostMapping("/sms/token")
	@ApiOperation(value="获取短信登录Token")
	public RestResult getSmsToken(){
		RestResult restResult = new RestResult();
		return restResult;
	}
	
}
