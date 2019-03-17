package org.dllwh.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 聊天室
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年12月9日 下午2:09:36
 * @版本: V1.0.1
 * @since: JDK 1.8
*/
@Controller
public class ChatRoomController {
	/**
	 * @方法描述 : 上线
	 */
	@MessageMapping("/upLine")
	public void upLine() {
		refreshLoginList();
	}

	/**
	 * @方法描述 : 下线
	 */
	@MessageMapping("downLine")
	public void downLine() {
		refreshLoginList();
	}

	/**
	 * @方法描述 : 服务器端通知客户端刷新当前登录人列表
	 */
	@MessageMapping("refreshLoginList")
	public void refreshLoginList() {

	}
}
