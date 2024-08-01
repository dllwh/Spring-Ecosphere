package org.dllwh.handler;

import java.util.List;

import org.dllwh.model.GroupUser;

import com.google.common.collect.Lists;
import com.google.gson.Gson;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: TODO(这里用一句话描述这个类的作用)
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2020-01-20
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
public class ChatGroupHelper {
	/**
	 * @方法描述: 处理组信息
	 *
	 */
	public static void handleGroup() {

	}

	/**
	 * @方法描述: 创建组
	 *
	 */
	public static void createWorkGroup() {

	}

	/**
	 * @方法描述: 解散组
	 *
	 */
	public static void destroyWorkGroup() {

	}

	/**
	 * @方法描述: 发送组用户
	 *
	 */
	public static void sendGroupUser() {

	}

	/**
	 * @方法描述: 增加用户到组
	 *
	 */
	public static void addGroupUser() {

	}

	/**
	 * @方法描述: 删除组中用户
	 *
	 */
	public static void delGroupUser() {

	}

	/**
	 * @方法描述: 退出组
	 *
	 */
	public static void ExitWorkGroup() {

	}

	/**
	 * @方法描述: 获取组成员
	 *
	 * @return
	 */
	public static String getGroupUsers() {
		List<GroupUser> groupUserList = Lists.newArrayList();
		// 在线的在列表前面
		List<GroupUser> onlineUserList = Lists.newArrayList();
		List<GroupUser> offlineUserList = Lists.newArrayList();
		onlineUserList.addAll(offlineUserList);
		return new Gson().toJson(groupUserList);
	}

	/**
	 * @方法描述: 发送消息到组所有在线成员
	 *
	 */
	public static void sendGroupMessage() {

	}

	/**
	 * 
	 * @方法描述: 获取工作组
	 *
	 * @param userid
	 * @return
	 */
	public static void getAllGroup(long userid) {

	}
}
