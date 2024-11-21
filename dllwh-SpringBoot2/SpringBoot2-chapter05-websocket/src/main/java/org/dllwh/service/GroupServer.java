package org.dllwh.service;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 用户组服务
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2019-12-25 9:32:30 PM
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
public interface GroupServer {
	/**
	 * @方法描述: 创建群组
	 * @param userids
	 * @return
	 */
	public void createGroup();

	/**
	 * @方法描述: 修改分组
	 */
	public void updateGroup();

	/**
	 * @方法描述: 删除分组
	 */
	public void deleteGroup();

	/**
	 * @方法描述: 加入群组group
	 */
	public void join();

	/**
	 * @方法描述: 退群
	 */
	public void exitGroup();

	/**
	 * @方法描述: 群发消息
	 * @param group
	 */
	public void writeToGroup(String group);

	/**
	 * @方法描述: 获取所有分组
	 * @param userName 
	 */
	public void getGroupList(String userName);

	/**
	 * @方法描述: 获取群成员
	 * @param group
	 */
	public void getGroupMemberList(String group);
}
