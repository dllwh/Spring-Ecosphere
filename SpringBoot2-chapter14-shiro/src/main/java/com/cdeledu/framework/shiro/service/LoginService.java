package com.cdeledu.framework.shiro.service;

import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.cdeledu.common.constant.SysLogConstant;
import com.cdeledu.common.util.SystemLogHelper;
import com.cdeledu.framework.model.LoggerEntity;
import com.cdeledu.framework.shiro.ShiroHelper;
import com.cdeledu.modules.system.domain.SysUser;
import com.google.common.collect.Maps;

import lombok.extern.slf4j.Slf4j;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 登录校验方法
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年12月9日 下午7:40:11
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@Component
@Slf4j
public class LoginService {

	@Autowired
	PasswordService passwordService;

	public String login(String userName, String password) throws AuthenticationException {
		Map<String, Object> resultMap = Maps.newConcurrentMap();
		password = passwordService.encryptPassword(userName, password);
		// 将用户名和密码封装到UsernamePasswordToken
		UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
		// 获得当前登录用户对象Subject，现在状态为 “未认证”
		Subject subject = SecurityUtils.getSubject();
		String logMsg = "", resultMsg = "";
		boolean suc = false;
		LoggerEntity loginEntity = new LoggerEntity();
		loginEntity.setLoginName(userName);

		try {
			subject.login(token);
		} catch (UnknownAccountException uae) {
			logMsg = "对用户[" + userName + "]进行登录验证..验证未通过,未知账户";
			resultMsg = "未知账户";
		} catch (IncorrectCredentialsException ice) {
			logMsg = "对用户[" + userName + "]进行登录验证..验证未通过,错误的凭证";
			resultMsg = "用户或密码错误";
		} catch (LockedAccountException lae) {
			logMsg = "对用户[" + userName + "]进行登录验证..验证未通过,账户已锁定";
			resultMsg = "账户已锁定";
		} catch (DisabledAccountException dae) {
			logMsg = "对用户[" + userName + "]进行登录验证..验证未通过,帐号已被禁用";
			resultMsg = "帐号已被禁用";
		} catch (ExpiredCredentialsException ece) {
			logMsg = "对用户[" + userName + "]进行登录验证..验证未通过,帐号已过期";
			resultMsg = "帐号已过期";
		} catch (ExcessiveAttemptsException eae) {
			logMsg = "对用户[" + userName + "]进行登录验证..验证未通过,用户名或密码错误次数过多";
			resultMsg = "用户名或密码错误次数过多";
		} catch (UnauthorizedException e) {
			logMsg = "对用户[" + userName + "]进行登录验证..验证未通过,您没有得到相应的授权！";
			resultMsg = "未授权";
		} catch (AuthenticationException ae) {
			logMsg = "对用户[" + userName + "]进行登录验证..验证未通过," + ae.getMessage();
			resultMsg = ae.getMessage();
		}

		if (subject.isAuthenticated()) {
			logMsg = "对用户[" + userName + "]进行登录验证..验证通过";
			suc = true;
		} else {
			token.clear();
		}

		if (log.isDebugEnabled()) {
			log.debug("此次登录结果，{}", logMsg);
		}

		// 存储登录日志
		if (suc) {
			SystemLogHelper.loginLog(userName, SysLogConstant.login.LOGIN_SUCCESS, logMsg);
		} else {
			SystemLogHelper.loginLog(userName, SysLogConstant.login.LOGIN_FAIL, logMsg);
		}
		resultMap.put("result", suc);
		resultMap.put("msg", resultMsg);

		return JSON.toJSONString(resultMap);
	}

	/**
	 * @方法描述:初始化权限
	 * @return
	 */
	public Map<String, String> loadFilterChainDefinitions() {
		return null;
	}

	/**
	 * @方法描述:重新加载权限
	 * @return
	 */
	public void updatePermission() {

	}

	/**
	 * @方法描述:通过角色id获取权限列表
	 * @return
	 */
	public List<String> getPermissionsByRoleId(Integer roleId) {
		return null;
	}

	/**
	 * @方法描述:根据角色id获取角色名称
	 * @return
	 */
	public String getRoleNameByRoleId(Integer roleId) {
		return null;
	}

	/**
	 * @方法描述:检查指定角色
	 * @return
	 */
	public boolean check(Object[] permissions) {
		SysUser user = ShiroHelper.getCurrenLoginUser();
		if (user == null) {
			return false;
		}
		return false;
	}

	/**
	 * @方法描述:检查全体角色
	 * @return
	 */
	public boolean checkAll() {
		SysUser user = ShiroHelper.getCurrenLoginUser();
		if (user == null) {
			return false;
		}
		return false;
	}
}