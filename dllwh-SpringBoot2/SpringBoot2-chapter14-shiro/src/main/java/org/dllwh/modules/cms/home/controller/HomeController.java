package org.dllwh.modules.cms.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.dllwh.framework.controller.BaseController;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 首页模块
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2019年1月9日 下午8:56:14
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Controller
@RequestMapping("home")
public class HomeController extends BaseController {

	@RequestMapping({ "", "/", "index" })
	public String index() {
		return "main/home";
	}
}