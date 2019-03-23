package org.dllwh.controller;

import java.util.List;

import org.dllwh.model.User;
import org.dllwh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("getUser")
	public List<User> getUserList() {
		return userService.getUserList();
	}
}