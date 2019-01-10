package com.cdeledu.modules.system.sysconfig.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cdeledu.common.RestResult;
import com.cdeledu.framework.controller.BaseController;
import com.cdeledu.modules.system.sysconfig.domain.SysArea;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 行政区域
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019年1月11日 上午12:05:01
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@Controller
@RequestMapping("sysArea")
@Api(value = "行政区域管理", tags = "行政区域管理")
public class SysAreaController extends BaseController {

	@RequestMapping("")
	public String index(ModelMap map) {
		return "system/area/areaInit";
	}

	@ResponseBody
	@ApiOperation(value = "列表")
	@RequestMapping(params = "getList")
	public List<SysArea> getList(SysArea sysArea) {
		return null;
	}

	@ResponseBody
	@RequestMapping("save")
	@ApiOperation(value = "创建")
	public RestResult save() {
		return RestResult.success();
	}

	@ResponseBody
	@RequestMapping("update")
	@ApiOperation(value = "更新")
	public RestResult update() {
		return RestResult.success();
	}

	@ResponseBody
	@RequestMapping("del")
	@ApiOperation(value = "删除")
	public RestResult del() {
		return RestResult.success();
	}
}