package org.dllwh.common.exception;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 基础异常
 * @创建者: 独泪了无痕
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年12月24日 下午5:18:17
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
public class BaseException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public BaseException() {
		super();
	}

	public BaseException(String msg) {
		super(msg);
	}
}
