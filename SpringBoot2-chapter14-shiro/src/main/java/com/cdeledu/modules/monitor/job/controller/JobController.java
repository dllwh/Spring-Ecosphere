package com.cdeledu.modules.monitor.job.controller;

import java.util.List;

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
import com.cdeledu.framework.controller.BaseController;
import com.cdeledu.modules.monitor.job.domain.Job;
import com.cdeledu.modules.monitor.job.service.JobService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述:调度任务信息操作处理
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年12月9日 下午7:14:57
 * @版本: V1.0.2
 * @since: JDK 1.8
 */
@Slf4j
@Controller
@RequestMapping("/monitor/job")
@Api(tags = "Quartz任务")
public class JobController extends BaseController {
	private String		prefix	= "monitor/job";
	@Autowired
	private JobService	scheduleJobService;
						
	@GetMapping()
	public String index() {
		return prefix + "/jobInit";
	}
	
	@ResponseBody
	@PostMapping(value = "create")
	@ApiOperation(value = "定时任务-新建任务")
	public RestResult create(Job scheduleJob) {
		if (log.isDebugEnabled()) {
			log.debug("新增任务");
		}
		if (scheduleJobService.save(scheduleJob) > 0) {
			return RestResult.success();
		} else {
			return RestResult.error();
		}
	}
	
	@ResponseBody
	@DeleteMapping(value = "remove/{jobId}")
	@ApiOperation(value = "定时任务-移除任务")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "你大爷还是你大爷，但是你大娘不一定是你大娘了") })
	public RestResult remove(@PathVariable("jobId") Integer jobId) {
		if (log.isDebugEnabled()) {
			log.debug("触发任务");
		}
		
		if (scheduleJobService.delete(jobId) > 0) {
			return RestResult.success();
		} else {
			return RestResult.error();
		}
	}
	
	@ResponseBody
	@DeleteMapping(value = "batchRemove")
	@ApiOperation(value = "定时任务-批量删除")
	public RestResult batchRemove(@RequestParam("ids[]") Integer[] ids) {
		if (log.isDebugEnabled()) {
			log.debug("定时任务-批量删除");
		}
		
		if (scheduleJobService.deleteBatch(ids) > 0) {
			return RestResult.success();
		} else {
			return RestResult.error();
		}
	}
	
	@ResponseBody
	@GetMapping("/update")
	@ApiOperation(value = "定时任务-修改任务")
	public RestResult update(Job scheduleJob) {
		if (scheduleJobService.update(scheduleJob) > 0) {
			return RestResult.success();
		} else {
			return RestResult.error();
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "getList", method = { RequestMethod.GET, RequestMethod.POST })
	@ApiOperation(value = "定时任务-任务列表")
	public List<Job> getList(Job scheduleJob, Integer pageNo, Integer pageSize) {
		if (log.isDebugEnabled()) {
			log.debug("任务列表");
		}
		return scheduleJobService.getJobList(scheduleJob);
	}
	
	/**
	 * @方法描述:定时任务信息
	 * @param jobId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/info/{jobId}")
	public Job info(@PathVariable("jobId") Integer jobId) {
		return scheduleJobService.getJobById(jobId);
	}
	
	@ResponseBody
	@PostMapping(value = "trigger")
	@ApiOperation(value = "定时任务-触发任务")
	public RestResult trigger(@RequestParam(name = "jobId") int jobId) {
		if (log.isDebugEnabled()) {
			log.debug("触发任务");
		}
		
		if (scheduleJobService.run(jobId) > 0) {
			return RestResult.success();
		} else {
			return RestResult.error();
		}
	}
	
	@ResponseBody
	@PostMapping(value = "pause")
	@ApiOperation(value = "定时任务-停止任务")
	public RestResult pause(@RequestParam(name = "jobId") int jobId) {
		if (log.isDebugEnabled()) {
			log.debug("停止任务");
		}
		
		if (scheduleJobService.pause(jobId) > 0) {
			return RestResult.success();
		} else {
			return RestResult.error();
		}
	}
	
	@ResponseBody
	@PostMapping(value = "resume")
	@ApiOperation(value = "定时任务-恢复任务")
	public RestResult resume(@RequestParam(name = "jobId") int jobId) {
		if (log.isDebugEnabled()) {
			log.debug("恢复任务");
		}
		
		if (scheduleJobService.resume(jobId) > 0) {
			return RestResult.success();
		} else {
			return RestResult.error();
		}
	}
}