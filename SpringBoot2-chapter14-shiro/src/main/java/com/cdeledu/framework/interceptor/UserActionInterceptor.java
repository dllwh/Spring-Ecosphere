package com.cdeledu.framework.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.cdeledu.common.RestResult;
import com.cdeledu.common.constant.ShiroConstants;
import com.cdeledu.common.constant.StatusMessage;
import com.cdeledu.common.util.WebHelper;
import com.cdeledu.framework.shiro.ShiroHelper;
import com.cdeledu.modules.system.upms.domain.SysUser;
import com.cdeledu.modules.system.upms.service.UserService;

import lombok.extern.slf4j.Slf4j;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 判断用户信息是否已被后台更改，并根据更改的情况做对应的处理
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2019年1月12日 下午2:13:55
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Slf4j
public class UserActionInterceptor implements HandlerInterceptor {
	@Autowired
	private UserService userService;

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.debug("请求到达后台方法之前调用（controller之前）");
		// 1. SecurityUtils获取session中的用户信息
		SysUser sysUser = ShiroHelper.getCurrenLoginUser();
		if (null != sysUser) {
			// 2. 获取数据库中的用户数据
			SysUser dataUser = userService.getUserByLoginName(sysUser.getUserName());
			// 3. 对比session中用户的version和数据库中的是否一致
			if (dataUser != null && sysUser == dataUser) {
				// 3.1 一样，放行
				return true;
			} else {
				// 3.2 不一样，这里统一做退出登录处理；// 使用redis缓存用户权限数据，根据用户更新、用户权限更新；做对应的处理。

				ShiroHelper.logout();
				if (WebHelper.isAjaxRequest(request)) {
					RestResult restResult = new RestResult();
					restResult.setStatusCode(StatusMessage.SystemStatus.UPDATE.getCode());
					WebHelper.out(response, restResult);
					return true;
				} else {
					// 重定向
					WebUtils.issueRedirect(request, response, ShiroConstants.KICKED_OUT);
				}
			}
		}
		return false;
	}

	public void postHandle(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse,
			Object obj, ModelAndView modelandview) throws Exception {
		log.debug("请求处理之后调用；在视图渲染之前，controller处理之后。");
	}

	public void afterCompletion(HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse, Object obj, Exception exception) throws Exception {
		log.debug("整个请求完成之后调用。DispatcherServlet视图渲染完成之后。（主要是用于进行资源清理工作）");
	}
}