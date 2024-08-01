package org.dllwh.service;

import java.util.List;

import org.dllwh.model.BaseGroup;
import org.dllwh.model.GroupUser;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 群组成员服务
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2020-01-12
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
public interface GroupUserService {

	/**
	 * @方法描述: 根据用户id查询用户已加入的群组
	 * @param userId
	 * @return
	 */
	List<BaseGroup> getByUserId(int userId);

	/**
	 * @方法描述: 批量添加群成员,会忽略已是群组成员的用户
	 * @param guserList 群成员list(必选值userId,groupId；可选值remarkName)
	 * @return 受影响行数
	 */
	int batchSave(List<GroupUser> guserList);

	/**
	 * @方法描述: 清空群成员
	 * @param groupId 群组id
	 * @return 受影响行数
	 */
	int deleteByGroupId(int groupId);

	/**
	 * @方法描述: 是否还有群组成员
	 * @param groupId 群组id
	 * @return true:有，false:无
	 */
	boolean hasUser(int groupId);

	/**
	 * @方法描述: 获取群成员列表,群组信息、用户登录信息和用户基本信息
	 * 
	 * @param groupId 群组id
	 * @return 群成员列表
	 */
	List<GroupUser> getByGroupId(int groupId);

	/**
	 * @方法描述: 获取可添加到讨论组的好友列表
	 *
	 * @param groupId
	 * @param userId
	 * @return
	 */
	List<GroupUser> getNewMemberByGroupId(int groupId, int userId);
}
