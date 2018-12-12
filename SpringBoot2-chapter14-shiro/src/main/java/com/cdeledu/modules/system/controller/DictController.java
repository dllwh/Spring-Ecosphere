package com.cdeledu.modules.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;

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
public class DictController {
	private String prefix = "system/dict";

	@GetMapping()
	public String index() {
		return prefix + "/index";
	}
}