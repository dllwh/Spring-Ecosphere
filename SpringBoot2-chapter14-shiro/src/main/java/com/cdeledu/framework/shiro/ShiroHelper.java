package com.cdeledu.framework.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: Shiro 帮助工具类
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年12月15日 下午11:17:41
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
public class ShiroHelper {
	
	public static void login(String userName, String passWord) {
	
	}
	
	/**
	 * @方法描述:获取认证授权组件Subject,其为我们提供了当前用户、角色和授权的相关信息
	 * @return
	 */
	public static Subject getSubjct() {
		return SecurityUtils.getSubject();
	}
	
	public static Session getSession() {
		return SecurityUtils.getSubject().getSession();
	}
	
	public static void logout() {
		getSubjct().logout();
	}
	
	public static String getIp() {
		return getSubjct().getSession().getHost();
	}
	
	public static String getSessionId() {
		return String.valueOf(getSubjct().getSession().getId());
	}
	
	public static void clearCachedAuthorizationInfo() {
		RealmSecurityManager rsm = (RealmSecurityManager) SecurityUtils.getSecurityManager();
		ShiroRealm realm = (ShiroRealm) rsm.getRealms().iterator().next();
		realm.clearCachedAuthorizationInfo();
	}
}
