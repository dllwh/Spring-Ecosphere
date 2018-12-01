package com.cdeledu.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.util.WebUtils;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: web 工具类
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年10月24日 上午8:32:52
 * @版本: V1.1.2
 * @since: JDK 1.8
 */
public final class WebHelperUtils {
	/**
	 * @方法描述 : 获取错误状态码,可以根据异常对象返回对应的错误信息
	 * @param request
	 * @return
	 */
	public static HttpStatus getErrorHttpStatus(HttpServletRequest request) {
		Integer statusCode = (Integer) request.getAttribute(WebUtils.ERROR_STATUS_CODE_ATTRIBUTE);
		try {
			return statusCode != null ? HttpStatus.valueOf(statusCode.intValue())
					: HttpStatus.INTERNAL_SERVER_ERROR;
		} catch (Exception ex) {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
	}
	
	/**
	 * @方法描述 : 获取堆栈轨迹
	 * @param error
	 * @param flag
	 * @return
	 */
	public static String getStackTraceInfo(Throwable error, boolean flag) {
		if (flag) {
			StringWriter stackTrace = new StringWriter();
			error.printStackTrace(new PrintWriter(stackTrace));
			stackTrace.flush();
			return stackTrace.toString();
		} else {
			return "omitted";
		}
	}
	
	/**
	 * 
	 * @方法描述 : 判断是否是AJAX请求
	 * @param request
	 * @return
	 */
	public static boolean isAjaxRequest(HttpServletRequest request) {
		return "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"));
	}
}