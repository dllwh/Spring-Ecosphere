package org.dllwh.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONObject;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 聊天室
 * @author: 独泪了无痕--duleilewuhen@sina.com
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

	/**
	 * 获取离线消息
	 */
	public void hideMessage() {

	}

	/**
	 * @方法描述: 获取好友分组和群
	 */
	public void friendList() {

	}

	/**
	 * @方法描述: 获取聊天记录
	 */
	public void chatRecord() {

	}

	/**
	 * @方法描述: 发送消息
	 */
	public void sendMessage() {
		JSONObject message = new JSONObject();
		JSONObject mine = new JSONObject();

		message.put("username", mine.getString("username"));
		message.put("avatar", mine.getString("avatar"));
		message.put("type", mine.getString("type"));
		message.put("content", mine.getString("content"));
		message.put("fromid", mine.getString("id"));
		message.put("timestamp", System.currentTimeMillis());

		// 好友发送消息
		if (mine.getString("type").equals("friend")) {
			// 判断接收着是否在线
			//  如果在线，发送信息保存到聊天信息数据库
			//  如果不在线，保存到离线信息数据库

		} else { // 群组消息
			// 保存到数据库
		}
	}

	/**
	 * @方法描述: 获取群成员
	 */
	public void getGroupMemberList() {

	}

	/**
	 * @方法描述: 根据id获取好友信息
	 * @param id
	 */
	public void getUserInfo(String id) {
	}

	/**
	 * @方法描述: 根据id获取群组信息
	 */
	public void getGroupInfo(String id) {
	}

	/**
	 * @方法描述: 通知所有在线好友,我上线了
	 */
	public void onlineMessage() {

	}

	/**
	 * @方法描述: 添加好友，添加群
	 */
	public void addFriendGroup() {

	}

	/**
	 * @方法描述: 同意添加好友
	 */
	public void addFriend() {

	}

	/**
	 * 获取消息通知,消息通知需要判断已读和未读，不然离线无法知道是否同意。
	 */
	public void checkFriendGroup() {

	}
}
