package org.dllwh.framework.shiro.session;

import java.io.Serializable;
import java.util.Date;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;

import org.dllwh.modules.monitor.online.domain.OnlineSession;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 针对shiro Session 服务
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019年1月6日 上午1:35:24
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
public class OnlineSessionDAO extends EnterpriseCacheSessionDAO {
	/** 上次同步数据库的时间戳 */
	private static final String LAST_SYNC_TIME = OnlineSessionDAO.class.getName() + "LAST_SYNC_TIME";

	/**
	 * 根据会话ID获取会话
	 */
	@Override
	protected Session doReadSession(Serializable sessionId) {
		return null;
	}

	/**
	 * 更新会话；如更新会话最后访问时间/停止会话/设置超时时间/设置移除属性等会调用
	 */
	public void syncToDb(OnlineSession onlineSession) {
		Date lastSyncTimestamp = (Date) onlineSession.getAttribute(LAST_SYNC_TIME);
		if (lastSyncTimestamp != null) {

		}

	}

	/**
	 * 当会话过期/停止（如用户退出时）属性等会调用
	 */
	@Override
	protected void doDelete(Session session) {

	}
}