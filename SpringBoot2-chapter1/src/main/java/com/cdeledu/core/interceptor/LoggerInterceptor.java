package com.cdeledu.core.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cdeledu.core.constant.PathConstant;
import com.cdeledu.model.LoggerEntity;
import com.cdeledu.utils.LoggerHelperUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 创建日志拦截器
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年10月10日 上午8:24:16
 * @版本: V1.1
 * @since: JDK 1.8
 */
@Slf4j
public class LoggerInterceptor implements HandlerInterceptor {
	// 请求开始时间标识
	private static final String	LOGGER_SEND_TIME	= "_send_time";
	// 请求日志实体标识
	private static final String	LOGGER_ENTITY		= "_logger_entity";
	// private SystemLogQueue systemLogQueue;
	
	/**
	 * 进入SpringMVC的Controller之前开始记录日志实体
	 * 
	 * @param request
	 *            请求对象
	 * @param response
	 *            响应对象
	 * 
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		String path = request.getServletPath();
		if (path.matches(PathConstant.NO_INTERCEPTOR_PATH)) { // 不需要的拦截直接过
			return true;
		} else {
			// 创建日志实体
			LoggerEntity logger = new LoggerEntity();
			// 获取请求sessionId
			String sessionId = request.getRequestedSessionId();
			// 请求路径
			String url = request.getRequestURI();
			// 获取请求参数信息
			String paramData = JSON.toJSONString(request.getParameterMap(),
					SerializerFeature.DisableCircularReferenceDetect,
					SerializerFeature.WriteMapNullValue);
			// 设置客户端ip
			logger.setClientIp(LoggerHelperUtils.getCliectIp(request));
			// 设置请求方法
			logger.setRequestMethod(request.getMethod());
			// 设置请求类型（json|普通请求）
			logger.setRequestType(LoggerHelperUtils.getRequestType(request));
			// 设置请求参数内容json字符串
			logger.setRequestParameter(paramData);
			// 设置请求地址
			logger.setRequestUrl(url);
			// 设置sessionId
			logger.setSessionId(sessionId);
			// 设置请求开始时间
			request.setAttribute(LOGGER_SEND_TIME, System.currentTimeMillis());
			// 设置请求实体到request内，方面afterCompletion方法调用
			request.setAttribute(LOGGER_ENTITY, logger);
			log.debug("拦截器LoggerInterceptor------->1、请求之前调用，也就是Controller方法调用之前。");
			return true; // 返回true则继续向下执行，返回false则取消当前请求
		}
	}
	
	public void postHandle(HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse, Object obj, ModelAndView modelandview)
					throws Exception {
		log.debug("拦截器LoggerInterceptor------->2、请求之后调用，在视图渲染之前，也就是Controller方法调用之后");
	}
	
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
			Object obj, Exception exception) throws Exception {
		if (request.getServletPath().matches(PathConstant.NO_INTERCEPTOR_PATH)) { // 不需要的拦截直接过
		} else {
			// 获取请求错误码
			int status = response.getStatus();
			// 当前时间
			long currentTime = System.currentTimeMillis();
			// 请求开始时间
			long time = Long.valueOf(request.getAttribute(LOGGER_SEND_TIME).toString());
			// 获取本次请求日志实体
			LoggerEntity loggerEntity = (LoggerEntity) request.getAttribute(LOGGER_ENTITY);
			// 设置请求时间差
			loggerEntity.setTimeConsuming(Long.valueOf((currentTime - time) + ""));
			// 设置返回时间
			loggerEntity.setReturnTime(currentTime + "");
			// 设置返回错误码
			loggerEntity.setHttpStatusCode(status + "");
			// 设置返回值
			// loggerEntity.setReturnData(JSON.toJSONString(request.getAttribute(LoggerHelperUtils.LOGGER_RETURN),SerializerFeature.DisableCircularReferenceDetect,SerializerFeature.WriteMapNullValue));
			// 执行将日志写入数据库
			log.debug("拦截器LoggerInterceptor------->3、请求结束之后被调用，主要用于清理工作。");
			// systemLogQueue.produce(loggerEntity);
		}
	}
}