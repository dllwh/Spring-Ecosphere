package org.dllwh.framework.shiro.filter;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import org.dllwh.common.constant.ShiroConstants;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: TODO(这里用一句话描述这个类的作用)
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019年1月6日 上午1:19:59
 * @版本: V1.0.1
 * @since: JDK 1.8
 * @see <a href="">TODO(连接内容简介)</a>
 */
public class OnlineSessionFilter extends AccessControlFilter {
	/**
	 * 表示是否允许访问；mappedValue就是[urls]配置中拦截器参数部分，如果允许访问返回true，否则false；
	 */
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		Subject subject = getSubject(request, response);
		if (subject == null || subject.getSession() == null) {
			return true;
		}
		return false;
	}

	/**
	 * 表示当访问拒绝时是否已经处理了；如果返回true表示需要继续处理；如果返回false表示该拦截器实例已经处理了，将直接返回即可。
	 */
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		Subject subject = getSubject(request, response);
		if (subject != null) {
			subject.logout();
		}
		saveRequestAndRedirectToLogin(request, response);
		return true;
	}

	/**
	 * 跳转到登录页
	 */
	@Override
	protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
		WebUtils.issueRedirect(request, response, ShiroConstants.LOGIN);
	}
}