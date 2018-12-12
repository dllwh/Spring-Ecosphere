package com.cdeledu.modules.monitor.job.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("job")
@Api(tags = "Quartz任务")
public class JobController {

	@RequestMapping("save")
	@ApiOperation(value = "新建任务")
	public String save(Job quartz) {
		log.info("新增任务");
		return "";
	}

	@PostMapping("list")
	@ApiOperation(value = "任务列表")
	public String list(Job quartz, Integer pageNo, Integer pageSize) {
		log.info("任务列表");
		return "";
	}

	@PostMapping("trigger")
	@ApiOperation(value = "触发任务")
	public String trigger(Job quartz) {
		log.info("触发任务");
		return "";
	}

	@PostMapping("pause")
	@ApiOperation(value = "停止任务")
	public String pause(Job quartz) {
		log.info("停止任务");
		return "";
	}

	@PostMapping("resume")
	@ApiOperation(value = "恢复任务")
	public String resume(Job quartz) {
		log.info("恢复任务");
		return "";
	}

	@PostMapping("remove")
	@ApiOperation(value = "移除任务")
	public String remove(Job quartz) {
		log.info("触发任务");
		return "";
	}
}