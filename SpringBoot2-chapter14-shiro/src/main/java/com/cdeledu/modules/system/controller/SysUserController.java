package com.cdeledu.modules.system.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cdeledu.common.RestResult;
import com.cdeledu.modules.system.domain.SysUser;
import com.cdeledu.modules.system.service.UserService;

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
	private String		prefix	= "/system/sysUser";
	@Autowired
	private UserService	userService;

	@GetMapping()
	public String index() {
		return prefix + "/index";
	}

	@GetMapping(value = "getList")
	@ApiOperation(value = "")
	@ResponseBody
	public List<SysUser> getList(SysUser sysUser) {
		return userService.getUserList(sysUser);
	}

	@ResponseBody
	@PostMapping(value = "save")
	@ApiOperation(value = "用户管理-保存用户(包括创建、修改)")
	public RestResult save(SysUser sysUser) {
		if (userService.saveUser(sysUser) > 0) {
			return RestResult.success();
		}
		return RestResult.error();
	}

	@ResponseBody
	@DeleteMapping(value = "remove/{userId}")
	@ApiOperation(value = "用户管理-删除用户")
	public RestResult remove(@PathVariable("userId") Integer userId) {
		SysUser sysUser = userService.getUserById(userId);
		if (sysUser == null) {
			return RestResult.error("用户不存在");
		}
		if (userId == 1) {
			return RestResult.error("超级管理员账号不支持删除");
		}
		if (userService.deleteUserById(userId) > 0) {
			return RestResult.success();
		}
		return RestResult.error();
	}

	@ResponseBody
	@DeleteMapping(value = "batchRemove")
	@ApiOperation(value = "用户管理-批量删除")
	public RestResult batchRemove(@RequestParam("ids[]") Integer[] ids) {
		int rows = userService.batchDeleteUser(ids);
		if (rows > 0) {
			return RestResult.success();
		}
		return RestResult.error();
	}

	@ResponseBody
	@PostMapping(value = "resetPwd")
	@ApiOperation(value = "用户管理-重置密码")
	public RestResult resetPwd(SysUser sysUser) {
		int rows = userService.resetUserPwd(sysUser);
		if (rows > 0) {
			return RestResult.success();
		} else {
			return RestResult.error();
		}
	}

	@ResponseBody
	@RequestMapping(value = "checkLoginNameUnique", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ApiOperation(value = "用户管理-校验用户名")
	public boolean checkLoginNameUnique(String userName) {
		if (StringUtils.isNoneBlank(userName)) {
			if (userService.checkLoginNameUnique(userName)) {
				return true;
			}
		}
		return false;
	}
}