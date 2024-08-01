package org.dllwh.websocket.service;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: TODO(这里用一句话描述这个类的作用)
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2020-01-24
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
public interface MessageProxy {
	/**
	 * @方法描述: 保存在线消息
	 *
	 */
	void saveOnlineMessageToDB();

	/**
	 * @方法描述: 保存离线消息
	 *
	 */
	void saveOfflineMessageToDB();

	/**
	 * @方法描述: 获取上线状态消息
	 *
	 * @param sessionId
	 */
	void getOnLineStateMsg(String sessionId);

	/**
	 * @方法描述: 重连状态消息
	 *
	 * @param sessionId
	 */
	void getReConnectionStateMsg(String sessionId);

	/**
	 * @方法描述: 获取下线状态消息
	 *
	 * @param sessionId
	 */
	void getOffLineStateMsg(String sessionId);
}
