package com.cdeledu.modules.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cdeledu.common.RestResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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
@Api(tags = "用户管理")
public class SysUserController {
	private String prefix = "/system/sysUser";
	
	@GetMapping()
	public String index() {
		return prefix + "/index";
	}
	
	@GetMapping(value = "getList")
	@ApiOperation(value = "")
	@ResponseBody
	public String getList() {
		return "";
	}
	
	@ResponseBody
	@PostMapping(value = "add")
	@ApiOperation(value = "用户管理-新增用户")
	public RestResult add() {
		return RestResult.success();
	}
	
	@ResponseBody
	@DeleteMapping(value = "remove")
	@ApiOperation(value = "用户管理-删除用户")
	public RestResult remove() {
		return RestResult.success();
	}
	
	@ResponseBody
	@DeleteMapping(value = "batchRemove")
	@ApiOperation(value = "用户管理-批量删除")
	public RestResult batchRemove() {
		return RestResult.success();
	}
	
	@ResponseBody
	@PostMapping(value = "edit")
	@ApiOperation(value = "用户管理-修改用户")
	public RestResult edit() {
		return RestResult.success();
	}
}