package org.dllwh.enumresource;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 响应状态码及信息
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年11月4日 下午11:46:25
 * @版本: V1.0.2
 * @since: JDK 1.8
 */
public enum ResponseCode {
	
	SUCCESS(200), // 成功
	FAIL(400), // 失败
	UNAUTHORIZED(401), // 未认证（签名错误）
	UNAUTHEN(4401), // 未登录
	UNAUTHZ(4403), // 未授权，拒绝访问
	INTERNAL_SERVER_ERROR(500),// 服务器内部错误
	;
	
	public int code;
	
	ResponseCode(int code) {
		this.code = code;
	}
}