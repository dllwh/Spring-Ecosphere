package com.cdeledu.framework.exception;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cdeledu.common.RestResult;

import lombok.extern.slf4j.Slf4j;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 自定义全局异常处理器
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年12月12日 下午10:47:57
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@Slf4j
@RestControllerAdvice
public class DefaultExceptionHandler {
	/**
	 * @方法描述:权限校验失败
	 * @param e
	 * @return
	 */
	public RestResult handleAuthorizationException(AuthorizationException e) {
		log.error(e.getMessage(), e);
		return RestResult.error("您没有数据的权限，请联系管理员添加");
	}
	
	/**
	 * @方法描述:请求方式不支持
	 * @param e
	 * @return
	 */
	@ExceptionHandler({ HttpRequestMethodNotSupportedException.class })
	public RestResult handleException(HttpRequestMethodNotSupportedException e) {
		log.error(e.getMessage(), e);
		return RestResult.error("不支持' " + e.getMethod() + "'请求");
	}
	
	/**
	 * @方法描述: 拦截未知的运行时异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler(RuntimeException.class)
	public RestResult notFount(RuntimeException e) {
		log.error("运行时异常:", e);
		return RestResult.error("运行时异常:" + e.getMessage());
	}
}