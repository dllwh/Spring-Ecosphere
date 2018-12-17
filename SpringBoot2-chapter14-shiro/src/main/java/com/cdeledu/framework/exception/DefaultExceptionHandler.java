package com.cdeledu.framework.exception;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cdeledu.common.RestResult;
import com.cdeledu.common.constant.SysLogConstant;
import com.cdeledu.common.util.WebHelper;
import com.cdeledu.framework.factory.TaskFactory;
import com.cdeledu.framework.model.LoggerEntity;
import com.cdeledu.framework.scheduled.ScheduledManager;

import lombok.extern.slf4j.Slf4j;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 自定义全局异常处理器,异常增强，以JSON的形式返回给客服端
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
	 */
	public RestResult handleAuthorizationException(HttpServletRequest request,
			AuthorizationException e) {
		log.error(e.getMessage(), e);
		saveExceptionLog(request, e);
		return RestResult.error("您没有数据的权限，请联系管理员添加");
	}
	
	/**
	 * @方法描述:请求方式不支持
	 */
	@ExceptionHandler({ HttpRequestMethodNotSupportedException.class })
	public RestResult handleException(HttpServletRequest request,
			HttpRequestMethodNotSupportedException e) {
		log.error(e.getMessage(), e);
		saveExceptionLog(request, e);
		return RestResult.error(405,"不支持' " + e.getMethod() + "'请求");
	}
	
	/**
	 * @方法描述:空指针异常
	 */
	@ExceptionHandler(NullPointerException.class)
	public RestResult nullPointerExceptionHandler(HttpServletRequest request,
			NullPointerException ex) {
		saveExceptionLog(request, ex);
		return RestResult.error(404, "空指针异常");
	}
	
	/**
	 * @方法描述:未知方法异常
	 */
	@ExceptionHandler(NoSuchMethodException.class)
	public RestResult noSuchMethodExceptionHandler(HttpServletRequest request,
			NoSuchMethodException ex) {
		saveExceptionLog(request, ex);
		return RestResult.error(404, "未知方法异常");
	}
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public RestResult handleMissingServletRequestParameterException(HttpServletRequest request,
			MissingServletRequestParameterException e) {
		saveExceptionLog(request, e);
		return RestResult.error(400, "缺少请求参数");
	}
	
	// 数组越界异常
	@ExceptionHandler(IndexOutOfBoundsException.class)
	public RestResult indexOutOfBoundsExceptionHandler(HttpServletRequest request,
			IndexOutOfBoundsException ex) {
		saveExceptionLog(request, ex);
		return RestResult.error(1005, ex.getMessage());
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public RestResult handleHttpMessageNotReadableExceptionHandler(HttpServletRequest request,
			HttpMessageNotReadableException e) {
		saveExceptionLog(request, e);
		return RestResult.error(400, "缺少请求参数");
	}
	
	@ExceptionHandler({ ConversionNotSupportedException.class,
			HttpMessageNotWritableException.class })
	public RestResult server500(HttpServletRequest request, Exception e) {
		log.error("运行时异常:", e);
		saveExceptionLog(request, e);
		return RestResult.error(500, "运行时异常:" + e.getMessage());
	}
	
	// 406错误
	@ExceptionHandler({ HttpMediaTypeNotAcceptableException.class })
	public RestResult request406(HttpServletRequest request, Exception e) {
		saveExceptionLog(request, e);
		return RestResult.error(406, null);
	}
	
	/**
	 * @方法描述: 拦截未知的运行时异常
	 */
	@ExceptionHandler(RuntimeException.class)
	public RestResult notFount(HttpServletRequest request, RuntimeException e) {
		log.error("运行时异常:", e);
		saveExceptionLog(request, e);
		return RestResult.error("运行时异常:" + e.getMessage());
	}
	
	private void saveExceptionLog(HttpServletRequest request, Throwable throwable) {
		LoggerEntity requestLog = new LoggerEntity();
		requestLog.setStartTime(new Timestamp(System.currentTimeMillis()));
		requestLog.setSessionId(request.getRequestedSessionId());
		requestLog.setRequestMethod(request.getMethod());
		if (WebHelper.isAjaxRequest(request)) {
			requestLog.setRequestType(1);
		} else {
			requestLog.setRequestType(0);
		}
		requestLog.setClientIp(request.getRemoteAddr());
		requestLog.setRequestUrl(request.getRequestURI());
		requestLog.setReturnData(
				JSON.toJSONString(throwable, SerializerFeature.DisableCircularReferenceDetect,
						SerializerFeature.WriteMapNullValue));
		requestLog.setExceptionDetail(throwable.getMessage());
		requestLog.setOperateStatus(WebHelper.getErrorHttpStatus(request).value());
		requestLog.setLogType(SysLogConstant.type.exception);
		
		ScheduledManager.getInstance().executeLog(TaskFactory.exceptionLog(requestLog));
	}
}