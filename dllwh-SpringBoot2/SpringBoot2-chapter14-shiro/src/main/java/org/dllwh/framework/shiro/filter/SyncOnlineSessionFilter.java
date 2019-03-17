package org.dllwh.framework.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.PathMatchingFilter;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 同步session 入库
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019年1月6日 上午1:21:48
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
public class SyncOnlineSessionFilter extends PathMatchingFilter {
	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
		return true;
	}
}