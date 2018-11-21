package com.cdeledu.cotroller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cdeledu.model.UserEntity;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述:
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年10月23日 上午10:44:46
 * @版本: V1.0
 * @since: JDK 1.8
 */
@Controller
@RequestMapping(value = "thymeleaf")
public class ThymeleafController {
	@GetMapping(value = {"/",""})
	public String index(Model model) {
		model.addAttribute("info", "thymeleaf/user/list");
		return "index";
	}

	@GetMapping("/user")
	public String user(Model model) {
		model.addAttribute("user",
				new UserEntity(UUID.randomUUID().toString(), "dllwh", "20181023"));
		return "user";
	}

	@GetMapping("/user/list")
	public String userlist(Model model) {
		List<UserEntity> userList = new ArrayList<>();
		userList.add(new UserEntity(UUID.randomUUID().toString(), "dllwh", "20181023"));
		userList.add(new UserEntity(UUID.randomUUID().toString(), "admin", "admin"));
		model.addAttribute("userList", userList);
		return "userList";
	}
}
