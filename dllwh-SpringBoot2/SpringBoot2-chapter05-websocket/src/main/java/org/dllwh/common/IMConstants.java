package org.dllwh.common;

import java.util.HashMap;

/**
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
public final class IMConstants {
	private static HashMap<String, String> cmd = null;
	private static HashMap<String, String> type = null;

	private static HashMap<String, String> getCmd() {
		if (cmd == null) {
			cmd = new HashMap<String, String>();
			cmd.put("refresh_user_list", "0");
			cmd.put("create_workgroup", "1");
			cmd.put("destroy_workgroup", "2");
			cmd.put("add_workgroup_user", "3");
			cmd.put("del_workgroup_user", "4");
			cmd.put("refresh_group_user", "5 ");
			cmd.put("system_user_login", "6");
			cmd.put("system_user_logout", "7");
			cmd.put("exit_workgroup", "8 ");
			cmd.put("sms_get_phone", "9");
			cmd.put("sms_send_msg", "10");
			cmd.put("open_user_win", "11");
			cmd.put("system_push", "12");
			cmd.put("system_user_status", "13");
		}
		return cmd;
	}

	private static HashMap<String, String> getType() {
		if (type == null) {
			type = new HashMap<String, String>();
			/** 系统消息 */
			type.put("SYSTEM", "system");
			/** 登录指令 */
			type.put("LOGIN", "login");
			/** 登出指令 */
			type.put("LOGOUT", "logout");
			/** 聊天消息 */
			type.put("CHAT", "chat");
		}
		return type;
	}

	public static String getCmd(String c) {
		return getCmd().get(c);
	}

	public static String getType(String t) {
		return getType().get(t);
	}
}
