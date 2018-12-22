package com.cdeledu.framework.controller;

import com.cdeledu.framework.shiro.ShiroHelper;
import com.cdeledu.modules.system.domain.SysUser;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: controller 层通用数据处理
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年12月13日 下午11:36:35
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
public class BaseController {
	public static SysUser getUser() {
		return ShiroHelper.getCurrenLoginUser();
	}
	
	public static void setUser(SysUser sysUser) {
		ShiroHelper.setCurrenLoginUser(sysUser);
	}
	
	/**
	 * @方法描述: 获取当前获取授权用户id.
	 * @return
	 */
	public static Integer getCurrentUserId() {
		return getUser().getId();
	}
	
	/**
	 * @方法描述: 获取当前获取授权用户用户名
	 * @return
	 */
	public static String getCurrentUserName() {
		return getUser().getUserName();
	}
}