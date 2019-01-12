package com.cdeledu.framework.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.AccessControlFilter;

import com.cdeledu.common.RestResult;
import com.cdeledu.common.util.WebHelper;
import com.cdeledu.framework.shiro.ShiroHelper;
import com.cdeledu.modules.system.upms.domain.SysUser;

import lombok.extern.slf4j.Slf4j;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 登录判断
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年12月15日 下午11:29:19
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@Slf4j
public class LoginFilter extends AccessControlFilter {

	/**
	 * @方法描述：表示是否允许访问
	 * 
	 *                <pre>
	 *                如果isAccessAllowed返回true则onAccessDenied方法不会继续执行
	 *                </pre>
	 * 
	 * @param mappedValue
	 *            就是[urls]配置中拦截器参数部分
	 * @return 如果允许访问返回true，否则false；
	 */
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("当前用户没有登录");
		}

		log.debug("----------{}----------", ((HttpServletRequest) request).getRequestURI());
		SysUser token = ShiroHelper.getCurrenLoginUser();
		if (token != null || isLoginRequest(request, response)) {
			return Boolean.TRUE;
		}

		if (WebHelper.isAjaxRequest((HttpServletRequest) request)) {
			log.debug("当前用户没有登录，并且是Ajax请求！");
			HttpServletResponse httpresponse = (HttpServletResponse) response;
			httpresponse.setHeader("sessionstatus", "timeout");
			WebHelper.out((HttpServletResponse) response, RestResult.error(201, "当前用户没有登录，需要重新登录"));
			return Boolean.FALSE;
		}
		return Boolean.FALSE;
	}

	/**
	 * @方法描述：表示当访问拒绝时是否已经处理了，
	 * 
	 *                        <pre>
	 * 如果返回true表示需要继续处理；如果返回false表示该拦截器实例已经处理了，将直接返回即可。
	 * onAccessDenied是否执行取决于isAccessAllowed的值，如果返回true则onAccessDenied不会执行；如果返回false，执行onAccessDenied。
	 * 如果onAccessDenied也返回false，则直接返回，不会进入请求的方法（只有isAccessAllowed和onAccessDenied的情况下）
	 *                        </pre>
	 * 
	 * @param
	 */
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		// 保存Request和Response 到登录后的链接
		log.error("*************************************保存Request和Response 到登录后的链接");
		if (!WebHelper.isAjaxRequest((HttpServletRequest) request)) {
			saveRequestAndRedirectToLogin(request, response);
		}
		return Boolean.FALSE;
	}
}
