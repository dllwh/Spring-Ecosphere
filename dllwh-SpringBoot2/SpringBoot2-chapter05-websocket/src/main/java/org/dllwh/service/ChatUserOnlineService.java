package org.dllwh.service;

import org.dllwh.model.OnlineUser;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 在线用户服务
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2020-01-02 11:58:22 PM
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
public interface ChatUserOnlineService {
	/**
	 * @方法描述: 增加在线用户
	 * @param onlineUser
	 * @return
	 */
	public boolean addOnlineUser(OnlineUser onlineUser);

	/**
	 * @方法描述: 增加在线用户
	 * @param onlineUser
	 * @return
	 */
	public boolean removeOnlineUser(OnlineUser onlineUser);

	/**
	 * 
	 * @方法描述: 获取登录用户信息
	 * @param userId
	 * @return
	 */
	public OnlineUser getOnlineUserByUserId(String userId);

	public void addUserChatQueue();

	public void addGroupChatQueue();

	public void sendGroupChat();

	public void sendUserChat();

	public void getUserChatMap();

	public void getGroupChatMap();

}
