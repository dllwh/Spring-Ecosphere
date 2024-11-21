package org.dllwh.service;

import org.dllwh.request.LoginRequest;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: webSocket 登录服务
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2020-01-12
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
public interface LoginChatService {
	/**
	 * @方法描述: 处理登录事件
	 * @param loginInfo 登录信息
	 * @return
	 */
	public boolean loginChatServerHandle(LoginRequest loginInfo);
}
