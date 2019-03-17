package org.dllwh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: MongoDB 控制类
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年11月24日 下午3:18:54
 * @版本: V 1.0.2
 * @since: JDK 1.8
 */
@Controller
@Slf4j
public class MongoController {

	/**
	 * @方法描述 : 模拟登录
	 */
	@ResponseBody
	@RequestMapping(value = "login")
	public void login(@RequestParam(name = "userName", required = true, defaultValue = "") String userName,
			@RequestParam(name = "pwd", required = true, defaultValue = "") String pwd) {
		log.info("模拟登录");
	}

	/**
	 * @方法描述 : 监控
	 * @param dbName
	 */
	@RequestMapping(value = "monitor")
	public String Monitor() {
		return "mongodb/mongoMonitor";
	}

	/**
	 * @方法描述 : 数据库对应的数据集合列表
	 * @param dbName
	 */
	@ResponseBody
	@RequestMapping(value = "db")
	public void db(@RequestParam(name = "dbName") String dbName) {
	}

	/**
	 * @方法描述 : 集合下的数据列表
	 */
	@ResponseBody
	@RequestMapping(value = "getCollection")
	public void getCollection() {

	}

	/**
	 * @方法描述 : 删除集合
	 */
	@ResponseBody
	@RequestMapping(value = "deleteCollection")
	public void deleteCollection() {

	}

	/**
	 * @方法描述 : 更新集合
	 */
	@ResponseBody
	@RequestMapping(value = "updateCollection")
	public void updateCollection() {

	}

	/**
	 * @方法描述 : 增加集合
	 */
	@ResponseBody
	@RequestMapping(value = "saveCollection")
	public void saveCollection() {

	}
}
