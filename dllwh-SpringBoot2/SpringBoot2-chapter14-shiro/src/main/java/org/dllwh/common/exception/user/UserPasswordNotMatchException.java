package org.dllwh.common.exception.user;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 用户密码不正确或不符合规范异常类
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年12月24日 下午5:12:55
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
public class UserPasswordNotMatchException extends UserException {
	private static final long serialVersionUID = 1L;

	public UserPasswordNotMatchException() {
		super("用户密码不正确");
	}

	public UserPasswordNotMatchException(String msg) {
		super(msg);
	}
}