package org.dllwh.framework.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.AccessControlFilter;

import lombok.extern.slf4j.Slf4j;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 要用于验证接口调用的 AccessToken 是否有效
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年12月30日 下午3:51:10
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Slf4j
public class AccessTokenFilter extends AccessControlFilter {

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object obj)
			throws Exception {
		log.debug("***************************************************************************************");
		return true;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		log.debug("***************************************************************************************");
		return false;
	}
}
