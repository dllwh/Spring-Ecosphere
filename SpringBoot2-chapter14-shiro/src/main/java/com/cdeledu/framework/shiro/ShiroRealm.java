package com.cdeledu.framework.shiro;

import java.sql.Timestamp;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.cdeledu.modules.system.domain.SysUser;
import com.cdeledu.modules.system.service.UserService;

import lombok.extern.slf4j.Slf4j;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: TODO(这里用一句话描述这个类的作用)
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年12月5日 下午10:57:30
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@Slf4j
public class ShiroRealm extends AuthorizingRealm {
	@Autowired
	private UserService userService;

	/**
	 * 权限认证
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		log.info("---------------- 执行 Shiro 权限获取 ---------------------");
		// 获取登录用户名
		// String userName = (String) principals.getPrimaryPrincipal();
		// SysUser token = ShiroHelper.getCurrenLoginUser();
		// SysUser sysUser = userService.getUserByLoginName(userName);
		// 添加角色和权限
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

		// 添加角色
		// info .addRole();
		// 添加权限
		// info .addStringPermission("");
		log.info("---- 获取到以下权限 ----");
		log.info(authorizationInfo.getStringPermissions().toString());
		log.info("---------------- Shiro 权限获取成功 ----------------------");
		return authorizationInfo;
	}

	/**
	 * 认证信息.(身份验证) : Authentication 是用来验证用户身份
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
			throws AuthenticationException {
		log.info("---------------- 执行 Shiro 凭证认证 ----------------------");

		// 加这一步的目的是在Post请求的时候会先进认证，然后在到请求
		if (authenticationToken.getPrincipal() == null) {
			return null;
		}
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		String userName = token.getUsername();
		String passWord = String.valueOf(token.getPassword());

		if (StringUtils.isBlank(userName)) {
			throw new UnknownAccountException();
		}

		if (StringUtils.isBlank(passWord)) {
			throw new IncorrectCredentialsException();
		}

		// 查询用户信息
		SysUser sysUser = userService.getUserByLoginName(userName);
		if (sysUser == null) {
			log.error("用户 { " + userName + " } 不存在 ");
			sysUser = userService.getUserByPhoneNumber(userName);
		}
		if (sysUser == null) {
			sysUser = userService.getUserByEmail(userName);
		}

		SimpleAuthenticationInfo authcInfo = null;

		if (sysUser == null) {
			throw new UnknownAccountException("账号不存在");
		}

		if (!sysUser.getPassword().equalsIgnoreCase(passWord)) {
			throw new IncorrectCredentialsException("密码不匹配");
		}

		if (sysUser.getIfEnabled() != 1) {
			throw new AuthorizationException("账号异常，请联系管理员");
		}

		if (sysUser.getIfVisible() != 1) {
			log.error("用户 { " + userName + " } 被禁止登录 ");
			throw new DisabledAccountException("账号未通过审核");
		}

		if (sysUser.getIfLocked() != 1) {
			log.error("用户 { " + userName + " } 被禁止登录 ");
			throw new LockedAccountException("账号被锁定，无法登录");
		}

		// 更新登录时间 last login time
		sysUser.setLoginDate(new Timestamp(System.currentTimeMillis()));

		// 清除授权信息
		// clearCachedAuthorizationInfo();
		// 这里验证authenticationToken和simpleAuthenticationInfo的信息
		authcInfo = new SimpleAuthenticationInfo(userName, passWord, getName());
		return authcInfo;
	}

	/**
	 * 清理缓存权限
	 */
	public void clearCachedAuthorizationInfo() {
		this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
	}
}
