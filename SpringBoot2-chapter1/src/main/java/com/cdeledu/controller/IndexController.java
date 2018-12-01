
package com.cdeledu.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
	private final static Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	/**
	 * @方法描述 : 返回首页
	 * @return
	 */
	@RequestMapping(value = "index")
	public String index() {
		logger.debug("记录debug日志");
		logger.info("访问了index方法");
		logger.error("记录error错误日志");
		
		return "index";
	}
}