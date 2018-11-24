package com.cdeledu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述:
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年11月24日 下午5:33:10
 * @版本: V 0.1
 * @since: JDK 1.8
 */
@Controller
public class WebSocketController {

	@RequestMapping(value = "/")
	public String index() {
		return "webSocket";
	}
}
