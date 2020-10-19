package org.dllwh.aop.controller;

import java.util.List;

import org.dllwh.aop.model.WebLog;
import org.dllwh.aop.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping(value = "OperLog")
public class OperLogController {

	@Autowired
	LogService logService;

	@ApiOperation("查询操作日志列表")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", dataType = "Long", name = "currentPage", value = "当前页码", required = true),
			@ApiImplicitParam(paramType = "query", dataType = "Long", name = "pageSize", value = "每页记录数", required = true),
			@ApiImplicitParam(paramType = "query", dataType = "String", name = "starttime", value = "开始时间 格式：yyyy-MM-dd HH:mm:ss", required = false),
			@ApiImplicitParam(paramType = "query", dataType = "String", name = "endtime", value = "结束时间 格式：yyyy-MM-dd HH:mm:ss", required = false) })
	@GetMapping(value = "getOperationLogList")
	public List<WebLog> getOperationLogList(@ApiIgnore WebLog operateLog) {
		operateLog.setLogType("info");
		return logService.getWebLogList(operateLog);
	}

	@ApiOperation("查询异常日志列表")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", dataType = "Long", name = "currentPage", value = "当前页码", required = true),
			@ApiImplicitParam(paramType = "query", dataType = "Long", name = "pageSize", value = "每页记录数", required = true),
			@ApiImplicitParam(paramType = "query", dataType = "String", name = "starttime", value = "开始时间 格式：yyyy-MM-dd HH:mm:ss", required = false),
			@ApiImplicitParam(paramType = "query", dataType = "String", name = "endtime", value = "结束时间 格式：yyyy-MM-dd HH:mm:ss", required = false) })
	@GetMapping(value = "getExceptionLogList")
	public List<WebLog> getExceptionLogList(@ApiIgnore WebLog exceptionLog) {
		exceptionLog.setLogType("error");
		return logService.getWebLogList(exceptionLog);
	}
}
