package org.dllwh.controller;

import java.util.List;
import java.util.Map;

import org.dllwh.model.Table;
import org.dllwh.service.SysGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/sys/generator")
@Api(tags = "代码生成器", value = "代码生成器")
public class SysGeneratorController {
	@Autowired
	private SysGeneratorService sysGeneratorService;

	@PostMapping("getTableList")
	@ApiOperation(value = "获取当前数据库表信息", notes = "获取当前数据库表信息")
	@ApiResponses({ @ApiResponse(code = 200, message = "ok", response = Table.class),
			@ApiResponse(code = 400, message = "业务逻辑异常"), @ApiResponse(code = 500, message = "服务器内部错误") })
	@ResponseBody
	public Map<String, Object> getTableList(
			@RequestParam(name = "pageSize", defaultValue = "10", required = true) int pageSize,
			@RequestParam(name = "pageNumber", defaultValue = "1", required = true) int pageNumber,
			@RequestParam("tableName") String tableName) {
		Map<String, Object> resultMap = Maps.newHashMap();
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("start", (pageNumber - 1) * pageSize);
		paramMap.put("offset", pageSize);
		paramMap.put("tableName", tableName);
		int totalCount = sysGeneratorService.getTableCount(paramMap);
		List<Map<String, Object>> tableList = null;

		if (totalCount > 0) {
			tableList = sysGeneratorService.getTableList(paramMap);
		}
		log.info("当前数据表个数：" + totalCount);
		log.info("当前数据表：" + tableList);
		resultMap.put("total", totalCount);
		resultMap.put("rows", tableList);

		return resultMap;
	}
}