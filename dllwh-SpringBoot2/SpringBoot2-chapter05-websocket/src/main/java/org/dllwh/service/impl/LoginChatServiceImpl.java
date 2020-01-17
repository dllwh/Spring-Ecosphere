package org.dllwh.service.impl;

import org.dllwh.request.LoginRequest;
import org.dllwh.service.LoginChatService;
import org.springframework.stereotype.Service;

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
@Service
public class LoginChatServiceImpl implements LoginChatService {
	@Override
	public boolean loginChatServerHandle(LoginRequest loginInfo) {
		// 1、判断是否存在用户
		// 2、获取登录用户的信息
		// 2.1 该用户在线，需要踢这个用户下线,才可以上线
		// 2.1.1 重新走登录请求
		// 2.2 该用户未在线
		// 2.1.2 走登录请求
		// 3、登录成功
		// 3.1 获取好友列表
		// 3.2 聊天组列表
		// 3.3 加入在线列表
		// 3.4 广播给在线好友上线
		// 4、登录失败
		// 4.1 发送登录抛异常，直接移出在线列表，不走下先流程了算登录失败

		return false;
	}
}
