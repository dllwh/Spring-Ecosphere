package org.dllwh.aop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
