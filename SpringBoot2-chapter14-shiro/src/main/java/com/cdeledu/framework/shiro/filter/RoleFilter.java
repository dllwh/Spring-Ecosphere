package com.cdeledu.framework.shiro.filter;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;

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
public class RoleFilter extends RolesAuthorizationFilter {
	@Override
	public boolean isAccessAllowed(ServletRequest request, ServletResponse response,
			Object mappedValue) throws IOException {
		return Boolean.TRUE;
	}
	
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response)
			throws IOException {
		return Boolean.TRUE;
	}
}