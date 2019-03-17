package org.dllwh.common.constant;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 相应信息状态
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2019年1月12日 下午2:05:55
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
public interface StatusMessage {
	public enum SystemStatus {
		// 请求成功
		SUCCESS(200, "SUCCESS"),
		// 请求失败
		ERROR(1001, "ERROR"),
		// 请求参数有误
		PARAM_ERROR(1002, "PARAM_ERROR"),
		// 表示成功匹配
		SUCCESS_MATCH(1003, "SUCCESS_MATCH"),
		// 未登录
		NO_LOGIN(1100, "NO_LOGIN"),
		// 多用户在线（踢出用户）
		MANY_LOGINS(1101, "MANY_LOGINS"),
		// 用户信息或权限已更新（退出重新登录）
		UPDATE(1102, "UPDATE"),
		// 用户已锁定
		LOCK(1111, "LOCK");

		private int		code;
		private String	message;

		private SystemStatus(int code, String message) {
			this.code = code;
			this.message = message;
		}

		public int getCode() {
			return this.code;
		}

		public String getMessage() {
			return this.message;
		}

		public static String getMessage(int code) {
			for (SystemStatus status : SystemStatus.values()) {
				if (status.code == code) {
					return status.message;
				}
			}
			return "";
		}
	}

	public enum ExceptionEnum {
		//
		UNKNOW_ERROR(-1, "UnknowError", "未知错误"),
		//
		USER_NOT_FIND(-101, "UserNotFind", "用户不存在"),
		//
		BAD_REQUEST(400, "BadRequest", "请求有误"),
		//
		FORBIDDEN(403, "Forbidden", "权限不足"),
		//
		NOT_FOUND(404, "NotFound", "您所访问的资源不存在"),
		//
		SERVER_EPT(500, "ServerEpt", "操作异常，请稍后再试");

		private Integer	type;
		private String	code;
		private String	msg;

		ExceptionEnum(Integer type, String code, String msg) {
			this.type = type;
			this.code = code;
			this.msg = msg;
		}

		public Integer getType() {
			return type;
		}

		public String getCode() {
			return code;
		}

		public String getMessage() {
			return msg;
		}
	}
}