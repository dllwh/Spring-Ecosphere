package com.cdeledu.framework.shiro.filter;

import java.io.IOException;
import java.io.Serializable;
import java.util.Deque;
import java.util.LinkedList;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import com.cdeledu.common.RestResult;
import com.cdeledu.common.constant.StatusMessage;
import com.cdeledu.common.util.WebHelper;
import com.cdeledu.modules.system.upms.domain.SysUser;

import lombok.extern.slf4j.Slf4j;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 自定义过滤器，进行用户访问控制
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年12月30日 下午4:07:16
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Slf4j
public class KickoutSessionFilter extends AccessControlFilter {
	// 踢出后到的地址
	private String								kickoutUrl;
	// 是否踢出后来登录的，默认是false；即后者登录的用户踢出前者登录的用户；踢出顺序。
	private boolean								kickoutAfter	= false;
	// 同一个用户最大的会话数，默认1；比如2的意思是同一个用户允许最多同时两个人登录；
	private int									maxSession		= 1;
	// 用于根据会话ID，获取会话进行踢出操作的；
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
		// 没有登录授权 且没有记住我
		if (!subject.isAuthenticated() && !subject.isRemembered()) {
			// 如果没有登录，直接进行之后的流程
			if (WebHelper.isAjaxRequest((HttpServletRequest) request)) {
				RestResult restResult = new RestResult();
				restResult.setStatusCode(StatusMessage.SystemStatus.MANY_LOGINS.getCode());
				restResult.setMessage("您已在别处登录，请您修改密码或重新登录");
				WebHelper.out(response, restResult);
				return false;
			}
			return true;
		}
		if (isLoginRequest(request, response)) {
			return true;
		}

		try {
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
					Session kickoutSession = sessionManager
							.getSession(new DefaultSessionKey(kickoutSessionId));
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

				return isAjaxResponse(request, response);
			}
			return true;
		} catch (Exception e) {
			log.error("控制用户在线数量【-->KickoutSessionFilter.onAccessDenied】异常！", e);
			return isAjaxResponse(request, response);
		}
	}

	private boolean isAjaxResponse(ServletRequest request, ServletResponse response) throws IOException {
		// 判断是不是Ajax请求
		if (WebHelper.isAjaxRequest((HttpServletRequest) request)) {
			WebHelper.out((HttpServletResponse) response, RestResult.error(300, "您已经在其他地方登录，请重新登录！"));
		} else {
			// 重定向
			WebUtils.issueRedirect(request, response, kickoutUrl);
		}
		return false;
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