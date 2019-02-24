package org.dllwh.cloud.feign.controller;

import org.dllwh.cloud.feign.model.User;
import org.dllwh.cloud.feign.service.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("feign-consumer")
public class FeignController {

	@Autowired
	private FeignService feignService;

	@GetMapping({ "", "/" })
	public String helloConsumer() {
		return feignService.sayHiFromClient();
	}

	/**
	 * @方法描述: 带有Request参数的请求
	 * @return
	 */
	@GetMapping("hello1")
	public String hello1(@RequestParam String userName) {
		return "Hello " + userName;
	}

	/**
	 * @方法描述: 带有Header信息的请求以及请求响应体是一个对象
	 * @return
	 */
	@GetMapping("hello2")
	public User hello2(@RequestHeader String userName, @RequestHeader String age) {
		return new User(userName, age);
	}

	/**
	 * @方法描述: 带有RequestBody的请求
	 * @return
	 */
	@GetMapping("hello3")
	public String hello3(@RequestBody User user) {
		return "Hello " + user.getUserName() + ", " + user.getAge();
	}
}