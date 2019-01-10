package com.cdeledu.modules.system.upms.controller;

import java.util.Arrays;
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
import org.springframework.web.multipart.MultipartFile;

import com.cdeledu.common.RestResult;
import com.cdeledu.framework.controller.BaseController;
import com.cdeledu.framework.shiro.service.PasswordService;
import com.cdeledu.modules.system.upms.domain.SysUser;
import com.cdeledu.modules.system.upms.service.UserService;

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
@Api(value = "用户管理", tags = "用户管理")
public class SysUserController extends BaseController {
	private String		prefix	= "/system/sysUser";
	@Autowired
	private UserService	userService;
	@Autowired
	PasswordService		passwordService;

	@ApiOperation(value = "用户首页")
	@GetMapping(value = { "", "/", "index" })
	public String index() {
		return prefix + "/index";
	}

	@ApiOperation(value = "用户数据")
	@GetMapping(value = "getList")
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
		// 不能删除 超级管理员 OR 当前登录用户
		if (userId == 1 || getCurrentUserId() == userId) {
			return RestResult.error("账号不支持删除");
		}

		if (userService.countUserRoleByUserId(userId) > 0) {
			return RestResult.error("删除失败，该用户已分配角色");
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
		// 不能删除 超级管理员 OR 当前登录用户
		if (Arrays.asList(ids).contains(1) || Arrays.asList(ids).contains(getCurrentUserId())) {
			return RestResult.error();
		}
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
			if (getUser().getId() == sysUser.getId()) {
				setUser(userService.getUserById(sysUser.getId()));
			}
			return RestResult.success();
		} else {
			return RestResult.error();
		}
	}

	@ResponseBody
	@RequestMapping(value = "checkPassword", method = { RequestMethod.GET, RequestMethod.POST })
	@ApiOperation(value = "密码检验")
	public boolean checkPassword(String password) {
		String encrypt = passwordService.encryptPassword(getUser().getUserName(), password, "");
		if (encrypt.equals(getUser().getPassword())) {
			return true;
		}
		return false;
	}

	@ResponseBody
	@RequestMapping(value = "checkLoginNameUnique", method = { RequestMethod.GET, RequestMethod.POST })
	@ApiOperation(value = "用户管理-校验用户名")
	public boolean checkLoginNameUnique(String userName) {
		if (StringUtils.isNoneBlank(userName)) {
			if (userService.checkLoginNameUnique(userName)) {
				return true;
			}
		}
		return false;
	}

	@PostMapping(value = "lockUser/{userId}/")
	@ApiOperation(value = "锁定/解锁用户")
	public RestResult lockUser(@PathVariable("userId") int userId,
			@RequestParam(name = "state", required = true) int state) {
		if (1 != userId && getCurrentUserId() != userId) {
			SysUser sysUser = new SysUser();
			sysUser.setId(userId);
			sysUser.setIfLocked(state);
			userService.updateUser(sysUser);
			return RestResult.success();
		} else {
			return RestResult.error();
		}
	}

	@PostMapping(value = "roleAssign", params = "saveUserRole")
	@ApiOperation(value = "用户-角色录入")
	public RestResult saveUserRole(@RequestParam("userId") Integer userId,
			@RequestParam(name = "roleIds", required = true) Integer[] roleIds) {
		// 管理员不参与
		if (roleIds != null && roleIds.length > 0) {
			if (Arrays.asList(roleIds).contains(1)) {
				return RestResult.error("无法授予【超级管理员】权限");
			}
			SysUser user = userService.getUserById(userId);
			if (user == null) {
				return RestResult.error("用户不存在");
			}
			userService.saveUserRole(userId, roleIds);
			return RestResult.success();
		} else {
			return RestResult.success();
		}
	}

	@PostMapping(value = "roleAssign", params = "clearUserRole")
	@ApiOperation(value = "根据用户ID清除角色(不包含超级管理)")
	public RestResult clearUserRole(@RequestParam("userId") Integer userId) {
		if (userId != null) {
			// 不能删除 超级管理员 OR 当前登录用户
			if (1 == userId || getCurrentUserId() == userId) {
				return RestResult.error();
			}

			// 后期会加上当前所有在线用户也不能删除
			if (userService.deleteUserRoleByUserId(userId) > 0) {
				return RestResult.success();
			}
		}
		return RestResult.error();
	}

	@PostMapping("/updateAvatar")
	@ApiOperation(value = "个人信息-保存头像(待完善)")
	public RestResult updateAvatar(SysUser user, @RequestParam("avatarfile") MultipartFile file) {
		try {
			if (!file.isEmpty()) {
				// String avatar = FileUploadUtils.upload(file);
				return RestResult.success();
			}
			return RestResult.error();
		} catch (Exception e) {
			return RestResult.error(e.getMessage());
		}
	}
}