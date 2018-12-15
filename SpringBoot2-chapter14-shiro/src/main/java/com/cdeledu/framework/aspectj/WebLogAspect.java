package com.cdeledu.framework.aspectj;

import java.sql.Timestamp;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cdeledu.common.constant.SysLogConstant;
import com.cdeledu.common.util.WebHelper;
import com.cdeledu.framework.model.LoggerEntity;
import com.cdeledu.framework.queue.SystemLogQueue;

import lombok.extern.slf4j.Slf4j;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: TODO(这里用一句话描述这个类的作用)
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年12月14日 下午7:10:28
 * @版本: V 0.1
 * @since: JDK 1.8
 */
@Aspect
@Component
@Slf4j
public class WebLogAspect {
	ThreadLocal<Long>		startTime	= new ThreadLocal<>();
	private LoggerEntity	requestLog	= new LoggerEntity();	// 创建日志实体
	private SystemLogQueue	auditLogQueue;
							
	@Resource
	public void setAuditLogQueue(SystemLogQueue auditLogQueue) {
		this.auditLogQueue = auditLogQueue;
	}
	
	/**
	 * @方法描述: Controoler切入点
	 * 		
	 *        拦截规则定义：拦截com.cdeledu.modules包及其子包下的所有类的所有方法
	 * 		
	 *        两个..代表所有子目录，最后括号里的两个..代表所有参数
	 */
	// @Pointcut("execution(public * com.cdeledu.modules..*.*(..))")
	@Pointcut("execution(public * com.cdeledu.modules..*.controller.*(..))")
	public void webLog() {
	}
	
	/**
	 * @方法描述: 前置通知，用于拦截记录用户的操作
	 */
	@Before("webLog()")
	public void doBeforeAdvice(JoinPoint joinPoint) throws Throwable {
		if (log.isDebugEnabled()) {
			log.debug("=========执行前置通知===============");
		}
		long beginTime = System.currentTimeMillis();
		startTime.set(beginTime);
	}
	
	/**
	 * @方法描述: 标注该方法体为后置通知,当目标方法执行成功后执行该方法体,使用在方法aspect()上注册的切入点
	 */
	@AfterReturning(returning = "returnValue", pointcut = "webLog()")
	public void doAfterReturning(JoinPoint joinPoint, Object returnValue) throws Throwable {
		if (log.isDebugEnabled()) {
			log.debug("=========执行后置返回通知===============");
		}
		// saveSysLog(joinPoint, returnValue, null);
	}
	
	/**
	 * @方法描述: 异常通知 用于拦截记录异常日志
	 */
	@AfterThrowing(pointcut = "webLog()", throwing = "e")
	public void doAfterAdvice(JoinPoint joinPoint, Throwable e) {
		if (log.isDebugEnabled()) {
			log.debug("=========执行异常通知===============");
		}
		saveSysLog(joinPoint, null, e);
	}
	
	private void saveSysLog(JoinPoint joinPoint, Object returnValue, Throwable throwable) {
		long finishTime = System.currentTimeMillis();
		// 接收到请求，记录请求内容
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		
		requestLog.setStartTime(new Timestamp(startTime.get()));
		requestLog.setSessionId(request.getRequestedSessionId());
		requestLog.setRequestMethod(request.getMethod());
		if (WebHelper.isAjaxRequest(request)) {
			requestLog.setRequestType(1);
		} else {
			requestLog.setRequestType(0);
		}
		
		// 从切面织入点处通过反射机制获取织入点处的方法
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		requestLog.setOperateClass(signature.getDeclaringTypeName());
		// 获取切入点所在的方法
		requestLog.setOperateMethod(signature.getMethod().getName());
		requestLog.setClientIp(request.getRemoteAddr());
		// 请求的参数
		Object[] args = joinPoint.getArgs();
		if (args != null && args.length > 0) {
			requestLog.setRequestParameter(
					JSON.toJSONString(args, SerializerFeature.DisableCircularReferenceDetect,
							SerializerFeature.WriteMapNullValue));
		}
		
		requestLog.setRequestUrl(request.getRequestURI());
		
		if (throwable != null) {
			requestLog.setReturnData(
					JSON.toJSONString(throwable, SerializerFeature.DisableCircularReferenceDetect,
							SerializerFeature.WriteMapNullValue));
			requestLog.setExceptionDetail(throwable.getMessage());
			requestLog.setOperateStatus(WebHelper.getErrorHttpStatus(request).value());
			requestLog.setLogType(SysLogConstant.type.exception);
		} else {
			// 处理完请求，返回内容
			requestLog.setReturnTime(new Timestamp(finishTime));
			requestLog.setReturnData(
					JSON.toJSONString(returnValue, SerializerFeature.DisableCircularReferenceDetect,
							SerializerFeature.WriteMapNullValue));
			requestLog.setTimeConsuming(finishTime - startTime.get());
			requestLog.setLogType(SysLogConstant.type.operate);
			requestLog.setOperateStatus(200);
		}
		// 加入队列
		auditLogQueue.produce(requestLog);
	}
}
