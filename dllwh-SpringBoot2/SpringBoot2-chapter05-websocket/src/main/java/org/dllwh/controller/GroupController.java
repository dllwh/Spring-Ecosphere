package org.dllwh.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 群组服务
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2020-01-12
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@RestController
@RequestMapping("api/group")
public class GroupController {
	/**
	 * @方法描述: 删除2分钟内的消息
	 *
	 * @param toId
	 * @param type 聊天类型：group
	 * @return false:撤回成功；false:撤回失败
	 */
	public boolean delMsgByCid(String toId, String type) {
		return false;
	}
}
