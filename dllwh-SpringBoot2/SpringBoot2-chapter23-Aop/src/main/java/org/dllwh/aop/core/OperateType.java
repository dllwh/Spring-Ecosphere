
package org.dllwh.aop.core;

/**
 * 操作类型
 */
public enum OperateType {

	UNKNOWN("unknown"),
	/** 添加 */
	INSERT("insert"),
	/** 删除 */
	DELETE("delete"),
	/** 更新 */
	UPDATE("update"),
	/** 查询 */
	SELECT("select"),
	/** 登录 */
	LOGIN("login"),
	/** 退出登录 */
	LOGOUT("logout");

	private String value;

	public String getValue() {
		return value;
	}

	OperateType(String s) {
		this.value = s;
	}
}
