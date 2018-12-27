package com.cdeledu.common.exception.user;

import com.cdeledu.common.exception.BaseException;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 用户信息异常
 * @创建者: 独泪了无痕
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年12月24日 下午5:24:46
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
public class UserException extends BaseException {
	private static final long serialVersionUID = 1L;

	public UserException() {
		super("用户信息异常");
	}

	public UserException(String msg) {
		super(msg);
	}
}
