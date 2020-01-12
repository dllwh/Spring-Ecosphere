package org.dllwh.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述:
 * @author: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年11月24日 下午5:33:10
 * @版本: V 0.1
 * @since: JDK 1.8
 */
@Controller
public class WebSocketController {

	@RequestMapping(value = "/")
	public String index() {
		return "ws";
	}

	@RequestMapping("onlineusers")
	@ResponseBody
	public Set<String> onlineusers(HttpSession session) {
		Set<String> nameset = new HashSet<String>();
		return nameset;
	}

	/**
	 * @方法描述: 发布系统广播（群发）
	 * @param text
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "broadcast", method = RequestMethod.POST)
	public void broadcast(@RequestParam("text") String text) throws IOException {

	}

	/**
	 * @方法描述: 获取连接服务器信息
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "serverInfo")
	public String serverInfo() {
		String result = "{\"code\":200,\"msg\":\"success\",\"list\":[{\"addr\":\"org.dllwh.com\",\"port\":8205,\"policyport\":843,\"weight\":1}]}";
		return result;
	}

	/**
	 * @方法描述: 获取连接服务器时间
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "getCurrentTime")
	public long getCurrentTime() {
		return DateTime.now().getMillis();
	}

	/**
	 * @方法描述: 获取连接服务器连接密钥
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "getWebSocketKey")
	public String getWebSocketKey(long time) {
		String result = "{\"code\":1,\"key\":\"df09d23445e39ba0c313a83ebcb81e87\",\"nowTime\":" + time + "}";
		return result;
	}

}
