package org.dllwh.modules.monitor.online.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.dllwh.modules.monitor.online.domain.UserOnline;
import org.dllwh.modules.monitor.online.mapper.UserOnlineMapper;
import org.dllwh.modules.monitor.online.service.UserOnlineService;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 在线用户服务层处理
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2019年1月4日 上午9:00:42
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Service
public class UserOnlineServiceImpl implements UserOnlineService {
	@Autowired
	private UserOnlineMapper userOnlineDao;

	/**
	 * @方法描述 : 通过会话序号查询信息
	 */
	@Override
	public UserOnline getOnlineUserByid(String sessionId) {
		return userOnlineDao.getOnlineUserByid(sessionId);
	}

	/**
	 * @方法描述 : 通过会话序号删除信息
	 */
	@Override
	public void deleteOnlineUserById(String sessionId) {
		userOnlineDao.deleteOnlineUserById(sessionId);
	}

	/**
	 * @方法描述 : 通过会话序号批量删除信息
	 */
	@Override
	public void batchDeleteOnline(List<String> sessions) {
		userOnlineDao.batchDeleteOnline(sessions);
	}

	/**
	 * @方法描述 : 保存会话信息
	 */
	@Override
	public void saveOnline(UserOnline online) {
		userOnlineDao.saveOnline(online);
	}

	/**
	 * @方法描述 : 查询会话集合
	 */
	@Override
	public List<UserOnline> gettUserOnlineList(UserOnline userOnline) {
		return userOnlineDao.gettUserOnlineList(userOnline);
	}

	/**
	 * @方法描述 : 查询会话集合的个数
	 */
	@Override
	public int gettUserOnlineListCount(UserOnline userOnline) {
		return userOnlineDao.gettUserOnlineListCount(userOnline);
	}
}