package com.cdeledu.core.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ErrorProperties.IncludeStacktrace;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerExceptionResolverComposite;
import org.springframework.web.util.WebUtils;

import com.cdeledu.core.baseBase.RestResult;
import com.cdeledu.utils.WebHelperUtils;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 错误信息构造器
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年10月27日 下午8:35:31
 * @版本: V1.0
 * @since: JDK 1.8
 */
@Component
public class ErrorInfoBuilder extends HandlerExceptionResolverComposite {
	// public class ErrorInfoBuilder implements HandlerExceptionResolver {
	private final static String	ERROR_NAME	= "springBootChapter.error";
	private ErrorProperties		errorProperties;						// 错误配置
								
	public ErrorProperties getErrorProperties() {
		return errorProperties;
	}
	
	public ErrorInfoBuilder(ServerProperties serverProperties) {
		this.errorProperties = serverProperties.getError();
	}
	
	/**
	 * @方法描述 :构造错误信息
	 * @param request
	 * @return
	 */
	public RestResult buildErrorInfo(HttpServletRequest request) {
		return buildErrorInfo(request, getError(request));
	}
	
	/**
	 * @方法描述 : 拿到最根部的error,携带手动抛出的异常信息
	 * 		
	 *       获取方式：通过Request对象获取(Key="javax.servlet.error.exception").
	 * @param request
	 * @return
	 */
	private Throwable getError(HttpServletRequest request) {
		// 根据HandlerExceptionResolver接口方法来获取错误
		Throwable error = (Throwable) request.getAttribute(ERROR_NAME);
		if (error == null) {
			// 根据Request对象获取错误.
			error = (Throwable) request.getAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE);
		}
		
		if (error != null) { // 当错误非空，取出RootCause
			while (error instanceof Exception && error.getCause() != null) {
				error = error.getCause();
			}
		} else { // 当错误为空,自定义错误消息
			String message = (String) request.getAttribute(WebUtils.ERROR_MESSAGE_ATTRIBUTE);
			if (StringUtils.isBlank(message)) {
				HttpStatus status = WebHelperUtils.getErrorHttpStatus(request);
				message = "Unknown Exception With " + status.value() + " "
						+ status.getReasonPhrase();
			}
			error = new Exception(message);
		}
		return error;
	}
	
	/**
	 * @方法描述 : 构建错误信息
	 * @param request
	 * @param error
	 * @return
	 */
	public RestResult buildErrorInfo(HttpServletRequest request, Throwable error) {
		RestResult errorInfo = new RestResult();
		errorInfo.setUrl(request.getRequestURL().toString());
		errorInfo.setError(error.toString());
		HttpStatus status = WebHelperUtils.getErrorHttpStatus(request);
		errorInfo.setStatusCode(status.value());
		errorInfo.setMessage(status.getReasonPhrase());
		errorInfo.setStackTrace(
				WebHelperUtils.getStackTraceInfo(error, isIncludeStackTrace(request)));
		return errorInfo;
	}
	
	/**
	 * @方法描述 : 判断是否包含堆栈轨迹
	 * @param request
	 * @return
	 */
	private boolean isIncludeStackTrace(HttpServletRequest request) {
		// 读取错误配置(server.error.include-stacktrace=NEVER)
		IncludeStacktrace includeStacktrace = errorProperties.getIncludeStacktrace();
		// 情况1：若IncludeStacktrace为ALWAYS
		if (includeStacktrace == IncludeStacktrace.ALWAYS) {
			return true;
		}
		// 情况2：若请求参数含有trace
		if (includeStacktrace == IncludeStacktrace.ON_TRACE_PARAM) {
			String parameter = request.getParameter("trace");
			return parameter != null && !"false".equals(parameter.toLowerCase());
		}
		// 情况3：其它情况
		return false;
	}
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
			Object handler, Exception exception) {
		request.setAttribute(ERROR_NAME, exception);
		return null;
	}
}