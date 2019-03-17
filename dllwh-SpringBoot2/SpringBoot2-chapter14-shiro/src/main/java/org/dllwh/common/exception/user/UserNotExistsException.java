package org.dllwh.common.exception.user;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 用户不存在异常
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年12月24日 下午5:12:22
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
public class UserNotExistsException extends UserException {
	private static final long serialVersionUID = 1L;

	public UserNotExistsException() {
		super("用户不存在异常");
	}

	public UserNotExistsException(String msg) {
		super(msg);
	}
}