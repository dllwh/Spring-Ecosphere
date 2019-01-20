package org.dllwh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 控制台(管理系统首页)
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2019年1月20日 下午3:10:04
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Controller
@RequestMapping("home")
public class HomeController {

	@GetMapping(value = "console")
	public ModelAndView console() {
		ModelAndView mv = new ModelAndView("console");
		mv.addObject("unreadMessage", true);
		return mv;
	}

	@GetMapping(value = "welcome")
	public ModelAndView welcome() {
		ModelAndView mv = new ModelAndView("welcome");
		return mv;
	}
}