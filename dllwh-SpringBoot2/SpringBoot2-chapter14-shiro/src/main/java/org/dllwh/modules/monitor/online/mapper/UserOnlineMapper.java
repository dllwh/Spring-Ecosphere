package org.dllwh.modules.monitor.online.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import org.dllwh.modules.monitor.online.domain.UserOnline;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 在线用户 数据层
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2019年1月4日 上午9:02:25
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Mapper
public interface UserOnlineMapper {
	/**
	 * @方法描述 : 通过会话序号查询信息
	 */
	UserOnline getOnlineUserByid(String sessionId);

	/**
	 * @方法描述 : 通过会话序号删除信息
	 */
	void deleteOnlineUserById(String sessionId);

	/**
	 * @方法描述 : 通过会话序号批量删除信息
	 */
	void batchDeleteOnline(List<String> sessions);

	/**
	 * @方法描述 : 保存会话信息
	 */
	void saveOnline(UserOnline online);

	/**
	 * @方法描述 : 查询会话集合
	 */
	List<UserOnline> gettUserOnlineList(UserOnline userOnline);

	/**
	 * @方法描述 : 查询会话集合的个数
	 */
	int gettUserOnlineListCount(UserOnline userOnline);
}
