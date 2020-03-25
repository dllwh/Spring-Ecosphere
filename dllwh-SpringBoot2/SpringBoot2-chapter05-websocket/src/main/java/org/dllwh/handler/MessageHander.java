package org.dllwh.handler;

import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

import org.dllwh.common.IMConstants;
import org.dllwh.model.IMMessage;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: webSocket消息中心，所有消息都会经过这里
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2020-01-07 12:25:32 AM
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
public final class MessageHander {
	// 记录每个session id发送过快的时间，防止用户恶意刷屏
	private Map<String, Date> timeLimitMap = new Hashtable<>();

	/**
	 * 
	 * @方法描述: 处理系统指令
	 *
	 * @param chatMessage
	 */
	public static void handleCommand(IMMessage chatMessage) {
		String cmd = chatMessage.getCmd();
		if (cmd.equals(IMConstants.getCmd("REFRESH_USER_LIST"))) {
			ChatMessageHelper.sendRefreshUserList();
		} else if (cmd.equals(IMConstants.getCmd("REFRESH_GROUP_USER"))) {
			ChatGroupHelper.sendGroupUser();
		} else if (cmd.equals(IMConstants.getCmd("CREATE_WORKGROUP"))) {
			ChatGroupHelper.createWorkGroup();
		} else if (cmd.equals(IMConstants.getCmd("DESTROY_WORKGROUP"))) {
			ChatGroupHelper.destroyWorkGroup();
		} else if (cmd.equals(IMConstants.getCmd("ADD_WORKGROUP_USER"))) {
			ChatGroupHelper.addGroupUser();
		} else if (cmd.equals(IMConstants.getCmd("DEL_WORKGROUP_USER"))) {
			ChatGroupHelper.delGroupUser();
		} else if (cmd.equals(IMConstants.getCmd("EXIT_WORKGROUP"))) {
			ChatGroupHelper.ExitWorkGroup();
		} else if (cmd.equals(IMConstants.getCmd("SMS_SEND_MSG"))) {
			ChatMessageHelper.sendSmsMsg();
		} else if (cmd.equals(IMConstants.getCmd("SYSTEM_USER_STATUS"))) {
			ChatMessageHelper.sendUserStatus();
		} else {
			System.out.println("not find command: " + cmd);
		}
	}

	/**
	 * @方法描述: 处理对话
	 *
	 */
	public static void handleMessage() {
		// 用于限制用户发送信息的频率，防止恶意刷屏
	}

	/**
	 * @方法描述: 给指定登录名发送
	 *
	 * @param loginName
	 * @param message
	 * @return
	 */
	public static boolean sendSystemMsg(String loginName, String message) {
		ChatMessageHelper.send(message);
		return false;
	}

	/**
	 * @方法描述: 给所有在线用户发送消息
	 *
	 * @param message
	 */
	public static void sendSystemMsgToAll(String message) {
	}

	/**
	 * @方法描述: 如果用户在线发送信息，否则发送短信
	 *
	 * @param to
	 * @param message
	 */
	public static void sendSystemMsg_Sms(long to, String message) {
	}

	/**
	 * @方法描述: 如果用户在线发送信息，否则发送短信
	 *
	 * @param to
	 * @param tel
	 * @param message
	 */
	public static void sendSystemMsg_Sms(long to, String tel, String message) {

	}

	/**
	 * @方法描述: 发送系统命令
	 *
	 * @param to  接收人ID
	 * @param cmd 命令，由前端注册方法名
	 * @return
	 */
	public static boolean sendSystemCmd(long to, String cmd) {
		ChatMessageHelper.send(cmd);
		return false;
	}

	/**
	 * 
	 * @方法描述: 强制退出
	 *
	 * @param userid 已登录用户ID
	 */
	public static void forceExit(long userid) {

	}

}
