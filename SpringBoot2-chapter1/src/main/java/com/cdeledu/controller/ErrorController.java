package com.cdeledu.controller;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: SpringBoot 统一异常处理 ErrorController
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年10月24日 下午3:23:27
 * @版本: V1.0
 * @since: JDK 1.8
 */
@RestController
@RequestMapping(value = "errorController")
public class ErrorController {
	/**
	 * 随机抛出异常.
	 */
	private void randomException() throws Exception {
		Exception[] exceptions = { // 异常集合
				new NullPointerException(), new ArrayIndexOutOfBoundsException(),
				new NumberFormatException(), new SQLException() };
		// 发生概率
		double probability = 0.75;
		if (Math.random() < probability) {
			// 情况1：要么抛出异常
			throw exceptions[(int) (Math.random() * exceptions.length)];
		} else {
			// 情况2：要么继续运行
		}

	}

	/**
	 * 模拟用户数据访问.
	 */
	@GetMapping("/")
	public List<String> index() throws Exception {
		randomException();
		return Arrays.asList("正常用户数据1!", "正常用户数据2! 请按F5刷新!!");
	}
}
