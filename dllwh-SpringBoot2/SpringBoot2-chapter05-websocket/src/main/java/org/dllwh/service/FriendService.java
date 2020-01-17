package org.dllwh.service;

import java.util.List;

import org.dllwh.model.UserInfo;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 用户好友服务
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2019-12-25 9:50:37 PM
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
public interface FriendService {
	/**
	 * @方法描述: 判断是否已是好友关系
	 * @param userId   用户id
	 * @param friendId 好友id
	 * @return true:已是好友 false不是好友
	 */
	boolean ifFriend(int userId, int friendId);

	/**
	 * 
	 * @方法描述: 添加好友
	 * @param userids
	 * @return
	 */
	boolean createFriend(String userids);

	/**
	 * @方法描述: 添加好友，添加群
	 */
	void addFriendGroup();

	/**
	 * @方法描述: 添加好友
	 * @param userId   用户id
	 * @param friendId 好友id
	 */
	void addFriend(int userId, int friendId);

	/**
	 * @方法描述: 拒绝审核
	 */
	void refuseFriend();

	/**
	 * @方法描述: 删除好友
	 * @param userId   用户id
	 * @param friendId 好友id
	 * @return true:已是好友 false不是好友
	 */
	void deleteFriend(int userId, int friendId);

	/**
	 * @方法描述: 获取好友列表
	 * @param userId
	 * @return
	 */
	boolean getFriendList(int userId);

	/**
	 * @方法描述: 获取好友列表
	 * @param userId
	 * @return int:好友数
	 */
	int getFriendCounts(int userId);

	/**
	 * @方法描述: 根据分组id查询用户分组下在线好友数
	 * @param id 分组id
	 * @return 在线好友数
	 */
	long getOnlineCountByGroupId(int id);

	/**
	 * @方法描述: 查询用户好友分组下所有好友列表（分页）
	 * @param id       分组id
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	List<UserInfo> getFriendByTypeIdPage(int id, int pageNum, int pageSize);

	/**
	 * @方法描述: 将用户移动至另一个分组
	 * @param userId   用户id
	 * @param friendId 好友id
	 * @param toTypeId 目标分组id
	 * @return 受影响行数
	 * @return
	 */
	int updateToOtherType(int userId, int friendId, int toTypeId);
}
