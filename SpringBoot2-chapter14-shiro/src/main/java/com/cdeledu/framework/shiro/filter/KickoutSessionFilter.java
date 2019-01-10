package com.cdeledu.framework.shiro.filter;

import java.io.Serializable;
import java.util.Deque;
import java.util.LinkedList;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import com.cdeledu.common.RestResult;
import com.cdeledu.common.util.WebHelper;
import com.cdeledu.modules.system.upms.domain.SysUser;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 限制并发人数登录
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年12月30日 下午4:07:16
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
public class KickoutSessionFilter extends AccessControlFilter {
	// 踢出后到的地址
	private String								kickoutUrl;
	// 踢出之前登录的/之后登录的用户 默认踢出之前登录的用户
	private boolean								kickoutAfter	= false;
	// 同一个帐号最大会话数 默认1
	private int									maxSession		= 1;

	private SessionManager						sessionManager;
	private Cache<String, Deque<Serializable>>	cache;

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse sresponse, Object obj)
			throws Exception {
		return false;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		Subject subject = getSubject(request, response);
		if (!subject.isAuthenticated() && !subject.isRemembered()) {
			// 如果没有登录，直接进行之后的流程
			return true;
		}

		Session session = subject.getSession();
		SysUser user = (SysUser) subject.getPrincipal();
		String username = user.getUserName();
		Serializable sessionId = session.getId();

		// 读取缓存 没有就存入
		Deque<Serializable> deque = cache.get(username);
		// 如果此用户没有session队列，也就是还没有登录过，缓存中没有就new一个空队列
		if (deque == null) {
			deque = new LinkedList<Serializable>();
		}

		// 如果队列里没有此sessionId，且用户没有被踢出；放入队列
		if (!deque.contains(sessionId) && session.getAttribute("kickout") == null) {
			// 将sessionId存入队列
			deque.push(sessionId);
			// 将用户的sessionId队列缓存
			cache.put(username, deque);
		}

		// 如果队列里的sessionId数超出最大会话数，开始踢人
		while (deque.size() > maxSession) {
			Serializable kickoutSessionId = null;
			if (kickoutAfter) {
				// 如果踢出后者
				kickoutSessionId = deque.removeFirst();
				// 踢出后再更新下缓存队列
				cache.put(username, deque);
			} else {
				// 否则踢出前者
				kickoutSessionId = deque.removeLast();
				// 踢出后再更新下缓存队列
				cache.put(username, deque);
			}

			try {
				// 获取被踢出的sessionId的session对象
				Session kickoutSession = sessionManager.getSession(new DefaultSessionKey(kickoutSessionId));
				if (kickoutSession != null) {
					// 设置会话的kickout属性表示踢出了
					kickoutSession.setAttribute("kickout", true);
				}
			} catch (Exception e) {// ignore exception
			}
		}

		// 如果被踢出了，直接退出，重定向到踢出后的地址
		if (session.getAttribute("kickout") != null) {
			// 会话被踢出了
			try {
				// 退出登录
				subject.logout();
			} catch (Exception e) { // ignore
			}
			saveRequest(request);

			// 判断是不是Ajax请求
			if (WebHelper.isAjaxRequest((HttpServletRequest) request)) {
				WebHelper.out(response, RestResult.error(300, "您已经在其他地方登录，请重新登录！"));
			} else {
				// 重定向
				WebUtils.issueRedirect(request, response, kickoutUrl);
			}
			return false;
		}
		return true;
	}

	public String getKickoutUrl() {
		return kickoutUrl;
	}

	public void setKickoutUrl(String kickoutUrl) {
		this.kickoutUrl = kickoutUrl;
	}

	public boolean isKickoutAfter() {
		return kickoutAfter;
	}

	public void setKickoutAfter(boolean kickoutAfter) {
		this.kickoutAfter = kickoutAfter;
	}

	public int getMaxSession() {
		return maxSession;
	}

	public void setMaxSession(int maxSession) {
		this.maxSession = maxSession;
	}
}