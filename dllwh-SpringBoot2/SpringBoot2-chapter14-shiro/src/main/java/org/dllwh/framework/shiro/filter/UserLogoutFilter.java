package org.dllwh.framework.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;

import org.dllwh.common.constant.SysLogConstant;
import org.dllwh.common.util.SystemLogHelper;
import org.dllwh.framework.shiro.ShiroHelper;
import org.dllwh.modules.system.upms.domain.SysUser;

import lombok.extern.slf4j.Slf4j;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 退出
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2019年1月4日 下午9:36:50
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Slf4j
public class UserLogoutFilter extends LogoutFilter {
	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
		try {
			Subject subject = getSubject(request, response);
			String redirectUrl = getRedirectUrl(request, response, subject);
			SysUser sysUser = (SysUser) ShiroHelper.getCurrenLoginUser();
			try {
				if (sysUser != null) {
					String userName = sysUser.getUserName();
					// 记录用户退出日志
					SystemLogHelper.loginLog(userName, SysLogConstant.login.LOGOUT, "成功退出");
				}
				// 退出登录
				ShiroHelper.logout();
			} catch (SessionException ise) {
				log.error("logout fail.", ise);
			}
			issueRedirect(request, response, redirectUrl);
		} catch (Exception e) {
			log.debug("Encountered session exception during logout.  This can generally safely be ignored.",
					e);
		}
		return false;
	}
}
