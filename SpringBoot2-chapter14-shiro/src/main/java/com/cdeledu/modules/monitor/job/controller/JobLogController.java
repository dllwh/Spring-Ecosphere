package com.cdeledu.modules.monitor.job.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cdeledu.common.RestResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 调度日志操作处理
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年12月9日 下午7:15:26
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@Controller
@RequestMapping("/monitor/jobLog")
@Api(tags = " 调度日志操作处理")
public class JobLogController {
	private String prefix = "monitor/job";
	
	@GetMapping()
	public String index() {
		return prefix + "/jobLogInit";
	}
	
	@ResponseBody
	@GetMapping(value = "getList")
	@ApiOperation(value = "定时任务日志列表")
	public String getList() {
		return "";
	}
	
	@ApiOperation(value = "定时任务日志信息")
	@RequestMapping("/info/{logId}")
	public String info(@PathVariable("logId") Long logId) {
		return "";
	}
	
	@ResponseBody
	@DeleteMapping(value = "remove/{jobLogId}")
	@ApiOperation(value = "定时任务-删除调度日志")
	public RestResult remove(@PathVariable("jobLogId") Integer jobLogId) {
		return RestResult.success();
	}
	
	@ResponseBody
	@DeleteMapping(value = "batchRemove")
	@ApiOperation(value = "定时任务-批量删除调度日志")
	public RestResult batchRemove(@RequestParam("ids[]") Integer[] ids) {
		return RestResult.success();
	}
}