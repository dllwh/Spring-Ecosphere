package org.dllwh.service;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 聊天服务
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2020-01-03 12:02:50 AM
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
public interface ChatService {

	/**
	 * @方法描述: 获取聊天组列表
	 */
	void getChatGroupList();

	/**
	 * @方法描述: 刷新在线用户列表
	 */
	void sendRefreshUserList();

	/**
	 * @方法描述:  是否在线
	 * @param userName
	 * @return true:是;false:否
	 */
	public boolean isOnline(String userName);

	/**
	 * @方法描述: 添加
	 * @param userName
	 */
	public void add(String userName);

	/**
	 * @方法描述: 移除
	 * @param userName
	 */
	public void remove(String userName);

	/**
	 * @方法描述: 获取聊天记录
	 */
	public void getChatRecord();

	/**
	 * @方法描述: 获取群聊天记录
	 */
	public void getFriendChatRecord();

	/**
	 * @方法描述: 获取聊天记录
	 */
	public void geChatGroupRecord();
}
