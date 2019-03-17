package org.dllwh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import org.dllwh.service.UserService;

@Controller
public class DataTableController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("dataTable")
	public ModelAndView dataTable(ModelAndView modelAndView) {
		modelAndView.addObject("userList", userService.getUserList());
		modelAndView.setViewName("dataTable");
		return modelAndView;
	}
}