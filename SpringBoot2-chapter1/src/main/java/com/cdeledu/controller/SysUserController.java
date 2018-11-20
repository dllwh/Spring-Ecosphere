package com.cdeledu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cdeledu.core.baseBase.RestResult;
import com.cdeledu.service.UserService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
public class SysUserController {
	@Autowired
	private UserService userService;

	@ApiOperation(value = "获取用户列表", notes = "获取用户列表")
	@RequestMapping(value = "users", method = RequestMethod.GET)
	public RestResult getUserList() {
		RestResult restResult = new RestResult();
		restResult.setStatusCode(200);
		restResult.setData(userService.getUserList());
		return restResult;
	}

	@ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
	@ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
	@RequestMapping(value = "user", method = RequestMethod.POST)
	public RestResult add() {
		RestResult restResult = new RestResult();
		restResult.setStatusCode(200);
		restResult.setData(userService.getUserList());
		return restResult;
	}

	@ApiOperation(value = "删除用户", notes = "根据url的id来指定删除用户")
	@RequestMapping(value = "delete", method = RequestMethod.DELETE)
	public RestResult delete() {
		RestResult restResult = new RestResult();
		restResult.setStatusCode(200);
		restResult.setData(userService.getUserList());
		return restResult;
	}

	@ApiOperation(value = "更新信息", notes = "根据url的id来指定更新用户信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "user", value = "用户实体user", required = true, dataType = "User") })
	@RequestMapping(value = "update", method = RequestMethod.PUT)
	public RestResult update() {
		RestResult restResult = new RestResult();
		restResult.setStatusCode(200);
		restResult.setData(userService.getUserList());
		return restResult;
	}
	
	@ApiIgnore//使用该注解忽略这个API
	@RequestMapping(value = "/hi", method = RequestMethod.GET)
	public String  jsonTest() {
		return " hi you!";
	}
}
