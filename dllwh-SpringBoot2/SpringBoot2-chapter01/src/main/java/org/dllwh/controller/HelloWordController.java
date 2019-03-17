package org.dllwh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.dllwh.service.UserService;

@RestController
@RequestMapping("hello")
public class HelloWordController {
	@Autowired
	private UserService userSerivce;
	
	@RequestMapping(value = "")
	public String hello() throws Exception {
		return "hello world";
	}
	
	@RequestMapping(value = "index")
	public String index() throws Exception {
		return "index";
	}
	
	@RequestMapping(value = "test")
	public Integer test() throws Exception {
		return userSerivce.getUserCount();
	}
}