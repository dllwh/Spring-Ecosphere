package com.cdeledu.modules.monitor.job.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cdeledu.common.RestResult;
import com.cdeledu.modules.monitor.job.domain.Job;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述:调度任务信息操作处理
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年12月9日 下午7:14:57
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@Slf4j
@RestController
@RequestMapping("/monitor/job")
@Api(tags = "Quartz任务")
public class JobController {
	private String prefix = "monitor/job";
	
	@GetMapping()
	public String index() {
		return prefix + "/jobInit";
	}
	
	@ResponseBody
	@PostMapping(value = "create")
	@ApiOperation(value = "定时任务-新建任务")
	public RestResult create(Job quartz) {
		log.info("新增任务");
		return RestResult.success();
	}
	
	@ResponseBody
	@DeleteMapping(value = "remove/{jobId}")
	@ApiOperation(value = "定时任务-移除任务")
	public RestResult remove(@PathVariable("jobId") Integer jobId) {
		log.info("触发任务");
		return RestResult.success();
	}
	
	@ResponseBody
	@DeleteMapping(value = "batchRemove")
	@ApiOperation(value = "定时任务-批量删除")
	public RestResult batchRemove(@RequestParam("ids[]") Integer[] ids) {
		log.info("定时任务-批量删除");
		return RestResult.success();
	}
	
	@ResponseBody
	@GetMapping("/edit/{jobId}")
	@ApiOperation(value = "定时任务-修改任务")
	public RestResult edit(@PathVariable("jobId") Long jobId, Model model) {
		return RestResult.success();
	}
	
	@RequestMapping(value = "getList", method = { RequestMethod.GET, RequestMethod.POST })
	@ApiOperation(value = "定时任务-任务列表")
	public String getList(Job quartz, Integer pageNo, Integer pageSize) {
		log.info("任务列表");
		return "";
	}
	
	@ResponseBody
	@PostMapping(value = "trigger")
	@ApiOperation(value = "定时任务-触发任务")
	public RestResult trigger(Job quartz) {
		log.info("触发任务");
		return RestResult.success();
	}
	
	@ResponseBody
	@PostMapping(value = "pause")
	@ApiOperation(value = "定时任务-停止任务")
	public RestResult pause(Job quartz) {
		log.info("停止任务");
		return RestResult.success();
	}
	
	@ResponseBody
	@PostMapping(value = "resume")
	@ApiOperation(value = "定时任务-恢复任务")
	public RestResult resume(Job quartz) {
		log.info("恢复任务");
		return RestResult.success();
	}
}