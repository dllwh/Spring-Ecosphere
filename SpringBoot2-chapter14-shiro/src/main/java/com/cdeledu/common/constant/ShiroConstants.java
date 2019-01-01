package com.cdeledu.common.constant;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: Shiro通用常量
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年12月14日 下午7:04:05
 * @版本: V 1.0.2
 * @since: JDK 1.7
 */
public interface ShiroConstants {
	/** 当前登录的用户 */
	public static final String	CURRENT_USER		= "currentUser";
	/** 用户名 */
	public static final String	CURRENT_USERNAME	= "username";
	/** 登录请求 */
	public final static String	LOGIN_ACTION		= "/login/ajaxLogin";
	/** 登录验证 */
	public final static String	LOGIN_CHECK			= "/login/checkuser";
	/** 踢出登录提示 */
	public final static String	KICKED_OUT			= "/login/kickout";
	/** 没有权限提醒 */
	public final static String	UNAUTHORIZED		= "/error/unauth";
}
