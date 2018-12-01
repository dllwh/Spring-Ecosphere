package com.cdeledu.core.constant;

import org.springframework.beans.factory.annotation.Value;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述:路径常量
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年10月10日 下午2:40:36
 * @版本: V1.1
 * @since: JDK 1.8
 */
public class PathConstant {
	/** 错误信息页的路径 */
	@Value("${server.error.path}")
	public static String		DEFAULT_ERROR_VIEW;
	/** 不验证URL anon：不验证/authc：受控制的 */
	public static final String	NO_INTERCEPTOR_PATH	= ".*/((.css)|(.js)|(images)|(login)|(anon)).*";
}