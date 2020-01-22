package org.dllwh.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: API 统一返回状态码
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2020-01-12
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Getter
@AllArgsConstructor
public enum ResultCode {
	/** 成功状态码 */
	SUCCESS(200, "操作成功"),
	/** 失败状态码 */
	FAILURE(500, "操作失败"),

	/**
	 * 参数错误：10001-19999
	 */
	PARAM_IS_INVALID(10001, "参数无效"),

	PARAM_IS_BLANK(10002, "参数为空"),

	PARAM_TYPE_BIND_ERROR(10003, "参数类型错误"),

	PARAM_NOT_COMPLETE(10004, "参数缺失"),

	/**
	 * 用户错误：20001-29999
	 */
	USER_NOT_LOGGED_IN(20001, "用户未登录,访问的路径需要验证，请登录"),

	USER_LOGIN_ERROR(20002, "账号不存在或密码错误"),

	USER_ACCOUNT_FORBIDDEN(20003, "账号已被禁用"),

	USER_NOT_EXIST(20004, "用户不存在"), USER_HAS_EXISTED(20005, "用户已存在"),

	/**
	 * 业务错误：30001-39999
	 */
	SPECIFIED_QUESTIONED_USER_NOT_EXIST(30001, "某业务出现问题"),

	APPLY_REPERT(30002, "您已经发送过好友申请，请勿重复发送！"),

	APPLY_EXIST(30003, "您已经添加该好友，请勿重复添加！"),

	/**
	 * 系统错误：40001-49999
	 */
	SYSTEM_INNER_ERROR(40001, "系统繁忙，请稍后重试"),

	UNKNOWN_ERROR(40002, "未知错误"),

	/**
	 * 数据错误：50001-599999
	 */
	RESULE_DATA_NONE(50001, "数据未找到"),

	DATA_IS_WRONG(50002, "数据有误"),

	DATA_ALREADY_EXISTED(50003, "数据已存在"),

	/**
	 * 接口错误：60001-69999
	 */
	INTERFACE_INNER_INVOKE_ERROR(60001, "内部系统接口调用异常"),

	INTERFACE_OUTTER_INVOKE_ERROR(60002, "外部系统接口调用异常"),

	INTERFACE_FORBID_VISIT(60003, "该接口禁止访问"),

	INTERFACE_ADDRESS_INVALID(60004, "接口地址无效"),

	INTERFACE_REQUEST_TIMEOUT(60005, "接口请求超时"),

	INTERFACE_EXCEED_LOAD(60006, "接口负载过高"),

	/**
	 * 权限错误：70001-79999
	 */
	PERMISSION_NO_ACCESS(70001, "无访问权限");

	private Integer code;
	private String message;

	public Integer getCode(String name) {
		for (ResultCode item : ResultCode.values()) {
			if (item.name().equals(name)) {
				return item.code;
			}
		}
		return null;
	}

	public String getMessage(String name) {
		for (ResultCode item : ResultCode.values()) {
			if (item.name().equals(name)) {
				return item.message;
			}
		}
		return name;
	}
}
