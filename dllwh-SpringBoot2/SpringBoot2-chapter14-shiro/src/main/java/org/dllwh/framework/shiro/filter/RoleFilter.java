package org.dllwh.framework.shiro.filter;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;

import org.dllwh.common.RestResult;
import org.dllwh.common.util.WebHelper;

import lombok.extern.slf4j.Slf4j;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 角色判断校验
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年12月15日 下午11:25:27
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@Slf4j
public class RoleFilter extends RolesAuthorizationFilter {
	@Override
	public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws IOException {
		if (log.isDebugEnabled()) {
			log.error("角色判断校验:isAccessAllowed");
		}
		String[] arra = (String[]) mappedValue;
		Subject subject = getSubject(request, response);

		for (String role : arra) {
			if (subject.hasRole(role)) {
				return Boolean.TRUE;
			}
		}

		if (WebHelper.isAjaxRequest((HttpServletRequest) request)) {
			if (log.isDebugEnabled()) {
				log.debug("当前用户的角色没有操作权限，并且是Ajax请求！");
			}
			WebHelper.out((HttpServletResponse) response, RestResult.error(201, "您未被授权使用该功能，请联系管理员进行处理。"));
			return Boolean.FALSE;
		}
		return Boolean.FALSE;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
		if (log.isDebugEnabled()) {
			log.error("角色判断校验:onAccessDenied");
		}
		Subject subject = getSubject(request, response);
		if (subject.getPrincipal() == null) {
			// 表示没有登录，重定向到登录页面
			saveRequest(request);
			WebUtils.issueRedirect(request, response, "");
		} else {
			// 如果有未授权页面，则跳转
			if (StringUtils.hasLength(getUnauthorizedUrl())) {
				WebUtils.issueRedirect(request, response, getUnauthorizedUrl());
			} else {
				// 否则返回401未授权状态码
				WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
			}
		}
		return Boolean.FALSE;
	}
}