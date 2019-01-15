package org.dllwh.controller;

import org.dllwh.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: redis监控页面Controller
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019年1月15日 下午11:06:49
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@Controller
@RequestMapping(value = "redis")
@Api
public class RedisController {
	@Autowired
	RedisService redisService;

	@ApiOperation(value = "跳转到监控页面")
	@RequestMapping(value = "redisMonitor")
	public String redisMonitor(Model model) {
		return "redisMonitor";
	}

	@ApiOperation(value = "清空日志按钮")
	@RequestMapping(value = "logEmpty")
	@ResponseBody
	public String logEmpty() {
		return redisService.logEmpty();
	}

	@ApiOperation(value = "获取当前数据库中key的数量")
	@RequestMapping(value = "getKeysSize")
	@ResponseBody
	public String getKeysSize() {
		return JSON.toJSONString(redisService.getKeysSize());
	}

	@ApiOperation(value = "获取当前数据库内存使用大小情况")
	@RequestMapping(value = "getMemeryInfo")
	@ResponseBody
	public String getMemeryInfo() {
		return JSON.toJSONString(redisService.getMemeryInfo());
	}
}