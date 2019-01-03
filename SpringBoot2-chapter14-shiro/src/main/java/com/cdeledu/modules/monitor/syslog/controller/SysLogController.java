package com.cdeledu.modules.monitor.syslog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cdeledu.common.RestResult;
import com.cdeledu.framework.controller.BaseController;
import com.cdeledu.framework.model.LoggerEntity;
import com.cdeledu.modules.monitor.syslog.domain.SysLoginLog;
import com.cdeledu.modules.monitor.syslog.service.SysLogService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述:
 * 
 * 		<pre>
 * 1、操作日志：系统操作日志记录（含异常）
 * 2、登录日志：系统登录情况记录（含异常）
 *       </pre>
 * 
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年12月5日 下午11:08:33
 * @版本: V1.0.2
 * @since: JDK 1.8
 */
@Controller
@RequestMapping("/monitor/sysLog")
@Api(tags = "系统日志记录")
public class SysLogController extends BaseController {
	private String			prefix	= "monitor/log";

	@Autowired
	private SysLogService	sysLogService;

	@GetMapping(value = "loginLog/index")
	@ApiOperation(value = "登录日志记录")
	public String loginLog() {
		return prefix + "/loginLog";
	}

	@ResponseBody
	@PostMapping(value = "loginLog/getList")
	@ApiOperation(value = "获取登录日志数据")
	public List<SysLoginLog> getloginLogList() {
		return sysLogService.getLoginLogList(null);
	}

	@ResponseBody
	@ApiOperation(value = "登录日志-批量删除")
	@DeleteMapping("batchRemove")
	public RestResult batchRemove() {
		return RestResult.error();
	}

	@ApiOperation(value = "操作日志记录")
	@GetMapping(value = "operateLog/index")
	public String operateLog() {
		return prefix + "/operateLog";
	}

	@ResponseBody
	@ApiOperation(value = "获取操作日志数据")
	@PostMapping(value = "operateLog/getList")
	public List<LoggerEntity> getOperateLogList() {
		return sysLogService.getOperateLogList(null);
	}

	@ApiOperation(value = "异常日志记录")
	@GetMapping(value = "excepLog/index")
	public String excepLog() {
		return prefix + "/excepLog";
	}

	@ResponseBody
	@ApiOperation(value = "获取异常日志")
	@PostMapping(value = "excepLog/getList")
	public List<LoggerEntity> getExcepLogList() {
		return sysLogService.getExpLogList(null);
	}
}