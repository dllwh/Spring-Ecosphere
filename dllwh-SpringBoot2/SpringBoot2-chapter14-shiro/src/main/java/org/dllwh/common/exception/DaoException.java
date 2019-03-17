package org.dllwh.common.exception;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述:TODO(这里用一句话描述这个类的作用)
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2019年1月4日 下午8:06:14
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
public class DaoException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DaoException(String message) {
		super(message);
	}

	public DaoException(Throwable cause) {
		super(cause);
	}

	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}
}