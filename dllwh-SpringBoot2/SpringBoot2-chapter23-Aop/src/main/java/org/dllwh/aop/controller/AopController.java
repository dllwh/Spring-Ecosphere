package org.dllwh.aop.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: TODO(这里用一句话描述这个类的作用)
 * 
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2020-08-20 08:47:36
 * @版本: V 1.0.1
 * @since: JDK 1.8
 *
 */
@RestController
@RequestMapping("aopController")
public class AopController {

	@RequestMapping(value = "exception")
	public void exception() {
		int i = 1/0;
	}

	@RequestMapping(value = "void")
	public void nOHaveReturnResult() {
		
	}

	@RequestMapping(value = "return")
	public String haveReturnResult() {
		String result = "{\"userName\":\"dllwh\",\"realName\":\"独泪了无痕\"}";
		return result;
	}

}
