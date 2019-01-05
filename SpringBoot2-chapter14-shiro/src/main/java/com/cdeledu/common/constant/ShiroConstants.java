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
 * @版本: V 1.0.3
 * @since: JDK 1.7
 */
public interface ShiroConstants {
	/** 当前登录的用户 */
	public static final String	CURRENT_USER		= "currentUser";
	/** 用户名 */
	public static final String	CURRENT_USERNAME	= "userName";
	/** 首页地址 */
	public static final String	indexUrl			= "/index";
	/** 登录地址 */
	public final static String	LOGIN				= "/login/";
	/** 登录请求 */
	public final static String	LOGIN_ACTION		= "/login/ajaxLogin";
	/** 登录验证 */
	public final static String	LOGIN_CHECK			= "/login/checkuser";
	/** 踢出登录提示 */
	public final static String	KICKED_OUT			= "/login/kickout";
	/** 权限认证失败地址 */
	public final static String	UNAUTHORIZED		= "/error/unauth";
	/** 同步session到数据库的周期（默认1分钟） */
	public final static int		dbSyncPeriod		= 1;
	/** 当前在线会话 */
	public final static String	ONLINE_SESSION		= "online_session";

	/** session的失效时长，设置为2个小时 */
	public final static long	TIMEOUT				= 72000000L;
	/** 定时清理失效session,设置为半小时 */
	public final static long	INTERVAL			= 1800000L;

	/** 用户状态 */
	public static enum OnlineStatus {

		on_line(1, "在线"), off_line(0, "离线");

		private Integer	code;
		private String	info;

		private OnlineStatus(Integer code, String info) {
			this.code = code;
			this.info = info;
		}

		public Integer getCode() {
			return code;
		}

		public String getInfo() {
			return info;
		}
	}
}