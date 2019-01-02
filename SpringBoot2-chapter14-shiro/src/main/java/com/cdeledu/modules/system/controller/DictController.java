package com.cdeledu.modules.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cdeledu.common.RestResult;
import com.cdeledu.framework.controller.BaseController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述:字典管理：对系统中经常使用的一些较为固定的数据进行维护
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年12月5日 下午11:07:47
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@Controller
@RequestMapping("/system/dict")
@Api(tags = "字典管理")
public class DictController  extends BaseController {
	private String prefix = "system/dict";

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
	@ApiOperation(value = "字典管理-新增字典")
	public RestResult add() {
		return RestResult.success();
	}
	
	@ResponseBody
	@DeleteMapping(value = "remove")
	@ApiOperation(value = "字典管理-删除字典")
	public RestResult remove() {
		return RestResult.success();
	}
	
	@ResponseBody
	@DeleteMapping(value = "batchRemove")
	@ApiOperation(value = "字典管理-批量删除")
	public RestResult batchRemove() {
		return RestResult.success();
	}
	
	@ResponseBody
	@PostMapping(value = "edit")
	@ApiOperation(value = "字典管理-修改字典")
	public RestResult edit() {
		return RestResult.success();
	}
}