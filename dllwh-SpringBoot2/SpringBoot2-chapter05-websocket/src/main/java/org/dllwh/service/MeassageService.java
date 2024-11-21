package org.dllwh.service;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 消息服务
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2019-12-29 11:41:06 PM
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
public interface MeassageService {
	/**
	 * @方法描述: 收到上线、 下线通知
	 * @param message
	 */
	public void changeStatus(String message);

	/**
	 * @方法描述: 判断用户是否在线
	 * @param userId
	 * @return
	 */
	public boolean isOnline(String userId);

	/**
	 * @方法描述: 发送离线消息
	 */
	void sendOfflineMessage();

	/**
	 * @方法描述: 获取离线消息
	 * @param userId
	 */
	public void getHidechat(String userId);

	/**
	 * @方法描述: 分页获取好友聊天记录
	 * @param to
	 * @param from
	 * @param pageNo
	 */
	public void getChatRecord(String to, String from, int pageNo);

	/**
	 * @方法描述: 获取群组聊天记录
	 * @param to
	 * @param from
	 * @param pageNo
	 */
	public void getGroupRecord(String to, String from, int pageNo);
}
