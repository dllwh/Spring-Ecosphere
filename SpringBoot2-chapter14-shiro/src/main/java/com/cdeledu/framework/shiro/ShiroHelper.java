package com.cdeledu.framework.shiro;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;

import com.cdeledu.common.constant.StatusMessage;
import com.cdeledu.modules.system.upms.domain.SysUser;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: Shiro 帮助工具类
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年12月15日 下午11:17:41
 * @版本: V1.0.2
 * @since: JDK 1.8
 */
public class ShiroHelper {

	public static void main(String[] args) {
		System.out.println(StatusMessage.SystemStatus.getMessage(200));
	}
	/**
	 * @方法描述:获取认证授权组件Subject,其为我们提供了当前用户、角色和授权的相关信息
	 * @return
	 */
	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	/**
	 * @方法描述 : 获取当前获取授权用户session
	 * @return
	 */
	public static Session getSession() {
		return SecurityUtils.getSubject().getSession();
	}

	/**
	 * @方法描述 : 获取当前获取授权用户信息
	 * @return
	 */
	public static SysUser getCurrenLoginUser() {
		return (SysUser) getSubject().getPrincipal();
	}

	public static void setCurrenLoginUser(SysUser user) {
		Subject subject = getSubject();
		PrincipalCollection principalCollection = subject.getPrincipals();
		String realmName = principalCollection.getRealmNames().iterator().next();
		PrincipalCollection newPrincipalCollection = new SimplePrincipalCollection(user, realmName);
		// 重新加载Principal
		subject.runAs(newPrincipalCollection);
	}

	/**
	 * @方法描述 : 退出登录
	 */
	public static void logout() {
		Subject subject = getSubject();
		if (subject.isAuthenticated()) {
			subject.logout();
		}
	}

	public static String getIp() {
		return getSubject().getSession().getHost();
	}

	public static String getSessionId() {
		return String.valueOf(getSubject().getSession().getId());
	}

	/**
	 * @方法描述 :认证通过或已记住的用户。
	 * @return 用户：true，否则 false
	 */
	public boolean isUser() {
		return isLogin();
	}

	/**
	 * @方法描述 : 验证当前用户是否为“访客”，即未认证（包含未记住）的用户。
	 * @return 访客：true，否则false
	 */
	public boolean isGuest() {
		return !isUser();
	}

	/**
	 * 判断是否登录
	 * 
	 * @return
	 */
	public static boolean isLogin() {
		return null != getSubject().getPrincipal();
	}

	public static void clearCachedAuthorizationInfo() {
		RealmSecurityManager rsm = (RealmSecurityManager) SecurityUtils.getSecurityManager();
		ShiroRealm realm = (ShiroRealm) rsm.getRealms().iterator().next();
		realm.clearCachedAuthorizationInfo();
	}

	/**
	 * @方法描述 : 验证当前用户是否属于该角色
	 * @param roleCode
	 * @return
	 */
	public static boolean hasRole(String roleCode) {
		return getSubject() != null && StringUtils.isNotBlank(roleCode) && getSubject().hasRole(roleCode);
	}

	/**
	 * 
	 * @方法描述:验证当前用户是否属于以下任意一个角色
	 * @param roleCodes
	 * @return
	 */
	public static boolean hasAnyRole(String roleCodes) {
		boolean hasAnyRole = false;
		Subject subject = getSubject();
		if (subject != null && roleCodes != null && roleCodes.length() > 0) {
			for (String role : roleCodes.split(",")) {
				if (subject.hasRole(role.trim())) {
					hasAnyRole = false;
					break;
				}
			}
		}
		return hasAnyRole;
	}

	/**
	 * @方法描述:验证当前用户是否属于以下所有角色
	 * @param roleNames
	 * @return
	 */
	public static boolean hasAllRoles(String roleNames) {
		boolean hasAllRole = true;
		Subject subject = getSubject();
		if (subject != null && roleNames != null && roleNames.length() > 0) {
			for (String role : roleNames.split(",")) {
				if (!subject.hasRole(role.trim())) {
					hasAllRole = false;
					break;
				}
			}
		}
		return hasAllRole;
	}

	/**
	 * @方法:验证当前用户是否拥有指定权限,使用时与lacksPermission 搭配使用
	 * @创建人:独泪了无痕
	 * @param permission
	 *            权限名
	 * @return 拥有权限：true，否则false
	 */
	public static boolean hasPermission(String permissionCode) {
		return getSubject() != null && StringUtils.isNotBlank(permissionCode)
				&& getSubject().isPermitted(permissionCode);
	}

	/**
	 * @方法:已认证通过的用户。不包含已记住的用户，这是与user标签的区别所在。与notAuthenticated搭配使用
	 * @创建人:独泪了无痕
	 * @return 通过身份验证：true，否则false
	 */
	public static boolean isAuthenticated() {
		return getSubject() != null && getSubject().isAuthenticated();
	}

	/**
	 * @方法:未认证通过用户，与authenticated标签相对应。与guest标签的区别是，该标签包含已记住用户。。
	 * @创建人:独泪了无痕
	 * @return 没有通过身份验证：true，否则false
	 */
	public static boolean notAuthenticated() {
		return !isAuthenticated();
	}
}