package org.dllwh.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dllwh.entity.Operate;
import org.dllwh.entity.RedisInfoDetail;
import org.dllwh.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
public class RedisClientController {
	@Autowired
	RedisService	redisService;
	@Value("${spring.redis.host}")
	private String	host;
	@Value("${spring.redis.port}")
	private String	port;

	
	@GetMapping(value = "console")
	public ModelAndView console() {
		ModelAndView mv = new ModelAndView("console");
		Map<String, Object> info = new HashMap<>();
		info.put("host", host);
		info.put("port", port);
		List<RedisInfoDetail> ridList = redisService.getRedisInfo();
		ridList.forEach(redisInfo -> {
			if("redis_version".equalsIgnoreCase(redisInfo.getKey())){
				info.put("redis_version", redisInfo.getValue());
			}
			if("uptime_in_days".equalsIgnoreCase(redisInfo.getKey())){
				info.put("uptime_in_days", redisInfo.getValue());
			}
			if("os".equalsIgnoreCase(redisInfo.getKey())){
				info.put("os", redisInfo.getValue());
			}
			if("connected_clients".equalsIgnoreCase(redisInfo.getKey())){
				info.put("connected_clients", redisInfo.getValue());
			}
			if("used_memory_human".equalsIgnoreCase(redisInfo.getKey())){
				info.put("used_memory_human", redisInfo.getValue());
			}
			if("blocked_clients".equalsIgnoreCase(redisInfo.getKey())){
				info.put("blocked_clients", redisInfo.getValue());
			}
			if("rdb_last_save_time".equalsIgnoreCase(redisInfo.getKey())){
				info.put("rdb_last_save_time", redisInfo.getValue());
			}
			
		});
		mv.addObject("info", info);
		return mv;
	}

	@ApiOperation(value = "跳转到监控页面")
	@RequestMapping(value = "redisMonitor")
	public String redisMonitor(Model model) {
		// 获取redis的info
		List<RedisInfoDetail> ridList = redisService.getRedisInfo();
		// 获取redis的日志记录
		List<Operate> logList = redisService.getLogs(1000);
		// 获取日志总数
		long logLen = redisService.getLogLen();
		model.addAttribute("infoList", ridList);
		model.addAttribute("logList", logList);
		model.addAttribute("logLen", logLen);

		return "redisMonitor";
	}

	@ApiOperation(notes = "首页获取redis info信息", value = "首页获取redis info信息")
	@GetMapping("getRedisInfo")
	@ResponseBody
	public List<RedisInfoDetail> getRedisInfo() {
		List<RedisInfoDetail> ridList = redisService.getRedisInfo();
		return ridList;
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