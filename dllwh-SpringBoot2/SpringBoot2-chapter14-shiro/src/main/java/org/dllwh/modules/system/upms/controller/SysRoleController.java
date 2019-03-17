package org.dllwh.modules.system.upms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.dllwh.common.RestResult;
import org.dllwh.framework.controller.BaseController;
import org.dllwh.modules.system.upms.domain.SysRole;
import org.dllwh.modules.system.upms.service.RoleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 角色菜单权限分配、设置角色进行数据范围权限划分
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年12月5日 下午10:39:15
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@Controller
@RequestMapping("/system/role")
@Api(value = "角色管理", tags = "角色管理")
public class SysRoleController extends BaseController {
	private String		prefix	= "system/role";
	@Autowired
	private RoleService	roleService;

	@GetMapping(value = { "", "/", "index" })
	@ApiOperation(value = "角色首页")
	public String index() {
		return prefix + "/index";
	}

	@ApiOperation(value = "角色列表")
	@GetMapping(value = "getList")
	@ResponseBody
	public List<SysRole> getList() {
		return roleService.getRoleList(null);
	}

	@ResponseBody
	@PostMapping(value = "add")
	@ApiOperation(value = "角色管理-新增角色")
	public RestResult add(SysRole sysRole) {
		if (roleService.saveRole(sysRole) > 0) {
			return RestResult.success();
		}
		return RestResult.error();
	}

	@ResponseBody
	@DeleteMapping(value = "remove/{roleId}")
	@ApiOperation(value = "角色管理-删除角色")
	public RestResult remove(@PathVariable("roleId") Integer roleId) {
		SysRole sysRole = roleService.getRoleById(roleId);
		if (sysRole == null) {
			return RestResult.error("角色不存在");
		}

		if (roleService.countUserRoleByRoleId(roleId) > 0) {
			return RestResult.error("角色已分配,不能删除");
		}

		if (roleService.deleteRoleById(roleId) > 0) {

			return RestResult.success();
		}
		return RestResult.error();
	}

	@ResponseBody
	@DeleteMapping(value = "batchRemove")
	@ApiOperation(value = "角色管理-批量删除")
	public RestResult batchRemove(@RequestParam("ids[]") Integer[] ids) {
		int rows = roleService.batchDeleteRole(ids);
		if (rows > 0) {
			return RestResult.success();
		}
		return RestResult.error();
	}

	@ResponseBody
	@PostMapping(value = "edit")
	@ApiOperation(value = "角色管理-修改角色")
	public RestResult edit() {
		return RestResult.success();
	}
}