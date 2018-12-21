package com.cdeledu.common.constant;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 日志系统中的定值
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年12月4日 下午9:48:42
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
public interface SysLogConstant {
	/**
	 * @类描述: 操作状态（成功与否Y\\N）
	 */
	static interface operateStatus {
		final String	Y	= "Y";	// 成功
		final String	N	= "N";	// 失败
	}

	/**
	 * @类描述: 日志类型
	 */
	static interface type {
		final String	operate		= "operate";	// 操作日志
		final String	exception	= "exception";	// 异常日志
		final String	login		= "login";		// 登录日志
		final String	logout		= "logout";		// 退出日志
	}

	static interface login {
		public static final String	LOGIN_SUCCESS	= "Success";	// 登录成功
		public static final String	LOGOUT			= "Logout";		// 注销
		public static final String	LOGIN_FAIL		= "Error";		// 登录失败
	}
}