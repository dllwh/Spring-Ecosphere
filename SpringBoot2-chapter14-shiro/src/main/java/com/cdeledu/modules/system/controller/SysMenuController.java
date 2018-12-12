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
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 配置系统菜单，操作权限，按钮权限标识等
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年12月5日 下午10:38:48
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@Controller
@RequestMapping("/system/menu")
@Api(tags = "系统菜单，操作权限")
public class SysMenuController {
	private String prefix = "system/menu";
	
	@GetMapping()
	public String index() {
		return prefix + "/index";
	}
	
	@GetMapping(value = "getList")
	@ApiOperation(value = "")
	public String getList() {
		return "";
	}
	
	@ResponseBody
	@PostMapping(value = "add")
	@ApiOperation(value = "菜单管理-新增菜单")
	public RestResult add() {
		return RestResult.success();
	}
	
	@ResponseBody
	@DeleteMapping(value = "remove")
	@ApiOperation(value = "菜单管理-删除菜单")
	public RestResult remove() {
		return RestResult.success();
	}
	
	@ResponseBody
	@DeleteMapping(value = "batchRemove")
	@ApiOperation(value = "菜单管理-批量删除")
	public RestResult batchRemove() {
		return RestResult.success();
	}
	
	@ResponseBody
	@PostMapping(value = "edit")
	@ApiOperation(value = "菜单管理-修改菜单")
	public RestResult edit() {
		return RestResult.success();
	}
}